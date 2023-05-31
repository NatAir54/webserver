package com.studying.webserver.server;


public class Starter {

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.setPort(3000);
        server.setWebAppPath("src/main/resources/webapp");
        server.start();
    }

}

