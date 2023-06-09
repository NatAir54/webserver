package com.studying.webserver.server;

import com.studying.webserver.io.RequestHandler;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class HttpServer {
    private int port;
    private String webAppPath;

    public void setPort(int port) {
        if (port < 1 || port > 1024) {
            this.port = 8080;
        }
        this.port = port;
    }

    public void setWebAppPath(String webAppPath) {
        if (!new File(webAppPath).exists()) {
            throw new IllegalArgumentException("Directory " + webAppPath + " not found");
        }
        this.webAppPath = webAppPath;
    }

    public void start() throws IOException {
        try (ServerSocket server = new ServerSocket(port)) {

            System.out.println("Server started.\nListening for connections on port: " + port + " ...\n");

            while (true) {
                try (Socket socket = server.accept();
                     BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     OutputStream out = socket.getOutputStream())
                {
                    RequestHandler requestHandler = new RequestHandler(br, out, webAppPath);
                    requestHandler.handle();
                }
            }
        }
    }
}
