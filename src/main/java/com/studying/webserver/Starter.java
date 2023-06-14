package com.studying.webserver;

import com.studying.webserver.server.HttpServer;

import java.io.IOException;


public class Starter {

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.setPort(3000);
        try {
            server.setWebAppPath("src/main/resources/webapp");
            server.start();
        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
        }
    }
}

