package pixelpro;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import pixelpro.mutation.MutationRegistry;
import pixelpro.server.StaticHandler;
import pixelpro.server.UploadHandler;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setProperty("java.awt.headless", "true");
        MutationRegistry filterRegistry = new MutationRegistry();
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", new StaticHandler());
        server.createContext("/upload", new UploadHandler(filterRegistry));
        server.start();

        System.out.println("Server successfully running on http://localhost:8080");
    }
}
 