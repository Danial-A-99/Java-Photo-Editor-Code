package pixelpro.server;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class StaticHandler implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {
        String pathStr = exchange.getRequestURI().getPath();

        if (pathStr.equals("/")) {
            pathStr = "/index.html";
        }

        Path filePath = Paths.get("public" + pathStr);

        if (Files.exists(filePath) && !Files.isDirectory(filePath)) {
            try {
                byte[] bytes = Files.readAllBytes(filePath);
                exchange.getResponseHeaders().set("Content-Type", getContentType(pathStr));
                exchange.sendResponseHeaders(200, bytes.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(bytes);
                }
            } catch (IOException e) {
                sendError(exchange, 500, "Error reading source file asset.");
            }
        } else {
            sendError(exchange, 404, "Static file asset not found.");
        }
    }

    private String getContentType(String path) {
        int lastDot = path.lastIndexOf('.');

        if (lastDot == -1) {
            return "application/octet-stream";
        }

        String extension = path.substring(lastDot).toLowerCase();

        return switch (extension) {
            case ".html" -> "text/html; charset=utf-8";
            case ".css" -> "text/css";
            case ".js" -> "application/javascript";
            case ".png" -> "image/png";
            case ".jpg", ".jpeg" -> "image/jpeg";
            case ".gif" -> "image/gif";
            case ".svg" -> "image/svg+xml";
            default -> "application/octet-stream";
        };
    }

    private void sendError(HttpExchange exchange, int status, String msg) throws IOException {
        byte[] errBytes = msg.getBytes();

        exchange.getResponseHeaders().set("Content-Type", "text/plain");
        exchange.sendResponseHeaders(status, errBytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(errBytes);
        }
    }
}
