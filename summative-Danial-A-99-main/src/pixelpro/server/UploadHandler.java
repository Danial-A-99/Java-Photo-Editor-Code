package pixelpro.server;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import pixelpro.mutation.Mutation;
import pixelpro.mutation.MutationRegistry;

public class UploadHandler implements HttpHandler {
    private final MutationRegistry mutationRegistry;

    public UploadHandler(MutationRegistry mutationRegistry) {
        this.mutationRegistry = mutationRegistry;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        try {
            Map<String, String> queryParams = parseQuery(exchange.getRequestURI().getQuery());
            String filterParam = queryParams.getOrDefault("filter", "grayscale");
            String format = normalizeFormat(queryParams.getOrDefault("format", "png"));
            byte[] inputBytes;

            try (InputStream is = exchange.getRequestBody()) {
                inputBytes = is.readAllBytes();
            }

            BufferedImage img = ImageIO.read(new ByteArrayInputStream(inputBytes));
            if (img == null) {
                System.err.println("Processing error: Stream content is malformed.");
                sendErrorResponse(exchange, 400);
                return;
            }

            for (String fName : filterParam.split(",")) {
                Mutation filter = mutationRegistry.getFilter(fName);
                if (filter != null) {
                    img = filter.apply(img, img.getWidth(), img.getHeight());
                }
            }

            sendImageResponse(exchange, img, format);

        } catch (Exception e) {
            System.err.println("Exception inside filtering pipeline: " + e.getMessage());
            sendErrorResponse(exchange, 500);
        }
    }

    private void sendImageResponse(HttpExchange exchange, BufferedImage img, String format) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, format, baos);
        byte[] responseBytes = baos.toByteArray();

        String contentType = switch (format) {
            case "gif" -> "image/gif";
            case "jpg", "jpeg" -> "image/jpeg";
            default -> "image/png";
        };

        exchange.getResponseHeaders().set("Content-Type", contentType);
        exchange.sendResponseHeaders(200, responseBytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
            os.flush();
        }
    }

    private void sendErrorResponse(HttpExchange exchange, int statusCode) {
        try {
            exchange.sendResponseHeaders(statusCode, -1);
            exchange.getResponseBody().close();
        } catch (IOException ioe) {
            System.err.println("Failed to send error response headers: " + ioe.getMessage());
        }
    }

    private String normalizeFormat(String format) {
        String lower = format.toLowerCase();
        return switch (lower) {
            case "png", "jpg", "jpeg", "gif" -> lower;
            default -> "png";
        };
    }

    private Map<String, String> parseQuery(String query) {
        Map<String, String> map = new HashMap<>();
        if (query == null) {
            return map;
        }

        for (String param : query.split("&")) {
            String[] kv = param.split("=", 2);
            if (kv.length == 2) {
                map.put(kv[0].toLowerCase(), kv[1]);
            }
        }
        return map;
    }
}