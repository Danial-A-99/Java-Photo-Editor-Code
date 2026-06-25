package pixelpro.server;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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
            exchange.getResponseHeaders().set("Allow", "POST");
            sendErrorResponse(exchange, 405, "Method Not Allowed.");
            return;
        }

        try {
            Map<String, String> queryParams = parseQuery(exchange.getRequestURI().getQuery());
            String filterParam = queryParams.getOrDefault("filter", "grayscale").trim();
            String format = normalizeFormat(queryParams.getOrDefault("format", "png").trim());
            byte[] inputBytes;

            try (InputStream is = exchange.getRequestBody()) {
                inputBytes = is.readAllBytes();
            }

            if (inputBytes.length == 0) {
                sendErrorResponse(exchange, 400, "Request body is empty.");
                return;
            }

            BufferedImage img = ImageIO.read(new ByteArrayInputStream(inputBytes));
            if (img == null) {
                sendErrorResponse(exchange, 400, "Request body is not a valid image.");
                return;
            }

            for (String fName : filterParam.split(",")) {
                String normalizedName = fName.trim().toLowerCase();
                if (normalizedName.isEmpty()) {
                    continue;
                }

                Mutation filter = mutationRegistry.getFilter(normalizedName);
                if (filter != null) {
                    img = filter.apply(img, img.getWidth(), img.getHeight());
                }
            }

            sendImageResponse(exchange, img, format);

        } catch (Exception e) {
            System.err.println("Exception inside filtering pipeline: " + e.getMessage());
            sendErrorResponse(exchange, 500, "Internal server error.");
        }
    }

    private void sendImageResponse(HttpExchange exchange, BufferedImage img, String format) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        boolean written = ImageIO.write(img, format, baos);
        if (!written) {
            sendErrorResponse(exchange, 500, "Unable to encode output image.");
            return;
        }

        byte[] responseBytes = baos.toByteArray();

        String contentType;
        switch (format) {
            case "gif":
                contentType = "image/gif";
                break;
            case "jpg":
            case "jpeg":
                contentType = "image/jpeg";
                break;
            default:
                contentType = "image/png";
                break;
        }

        exchange.getResponseHeaders().set("Content-Type", contentType);
        exchange.sendResponseHeaders(200, responseBytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
        }
    }

    private void sendErrorResponse(HttpExchange exchange, int statusCode, String message) throws IOException {
        byte[] errBytes = message.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=utf-8");
        exchange.sendResponseHeaders(statusCode, errBytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(errBytes);
        }
    }

    private String normalizeFormat(String format) {
        String lower = format.toLowerCase();
        switch (lower) {
            case "png":
            case "jpg":
            case "jpeg":
            case "gif":
                return lower;
            default:
                return "png";
        }
    }

    private Map<String, String> parseQuery(String query) {
        Map<String, String> map = new HashMap<>();
        if (query == null || query.isBlank()) {
            return map;
        }

        for (String param : query.split("&")) {
            String[] kv = param.split("=", 2);
            if (kv.length == 2) {
                String key = URLDecoder.decode(kv[0], StandardCharsets.UTF_8);
                String value = URLDecoder.decode(kv[1], StandardCharsets.UTF_8);
                map.put(key.toLowerCase(), value);
            }
        }
        return map;
    }
}
