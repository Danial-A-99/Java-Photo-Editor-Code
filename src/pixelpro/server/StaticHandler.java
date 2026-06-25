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
        String method = exchange.getRequestMethod();
        if (!method.equalsIgnoreCase("GET") && !method.equalsIgnoreCase("HEAD")) {
            exchange.getResponseHeaders().set("Allow", "GET, HEAD");
            sendError(exchange, 405, "Method Not Allowed.");
            return;
        }

        String pathStr = exchange.getRequestURI().getPath();
        if (pathStr == null || pathStr.isBlank()) {
            pathStr = "/index.html";
        } else if (pathStr.equals("/")) {
            pathStr = "/index.html";
        }

        Path root = Paths.get("public").toAbsolutePath().normalize();
        Path filePath = root.resolve("." + pathStr).normalize();

        if (!filePath.startsWith(root) || !Files.exists(filePath) || Files.isDirectory(filePath)) {
            sendError(exchange, 404, "Static file asset not found.");
            return;
        }

        try {
            byte[] bytes = Files.readAllBytes(filePath);
            exchange.getResponseHeaders().set("Content-Type", getContentType(pathStr));
            int responseLength = method.equalsIgnoreCase("HEAD") ? 0 : bytes.length;
            exchange.sendResponseHeaders(200, responseLength);

            if (method.equalsIgnoreCase("GET")) {
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(bytes);
                }
            } else {
                exchange.getResponseBody().close();
            }
        } catch (IOException e) {
            sendError(exchange, 500, "Error reading source file asset.");
        }
    }

    private String getContentType(String path) {
        int lastDot = path.lastIndexOf('.');
        if (lastDot == -1) {
            return "application/octet-stream";
        }

        String extension = path.substring(lastDot).toLowerCase();
        String contentType;
        switch (extension) {
            case ".html":
                contentType = "text/html; charset=utf-8";
                break;
            case ".css":
                contentType = "text/css";
                break;
            case ".js":
                contentType = "application/javascript";
                break;
            case ".png":
                contentType = "image/png";
                break;
            case ".jpg":
            case ".jpeg":
                contentType = "image/jpeg";
                break;
            case ".gif":
                contentType = "image/gif";
                break;
            case ".svg":
                contentType = "image/svg+xml";
                break;
            case ".json":
                contentType = "application/json";
                break;
            case ".txt":
                contentType = "text/plain; charset=utf-8";
                break;
            default:
                contentType = "application/octet-stream";
                break;
        }
        return contentType;
    }

    private void sendError(HttpExchange exchange, int status, String msg) throws IOException {
        byte[] errBytes = msg.getBytes();
        exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=utf-8");
        exchange.sendResponseHeaders(status, errBytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(errBytes);
        }
    }
}
