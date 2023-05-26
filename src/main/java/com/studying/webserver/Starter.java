package com.studying.webserver;

import java.io.IOException;

public class Starter {

    public static void main(String[] args) throws IOException {
        HttpServer server = new HttpServer();
        server.setPort(3000);
        server.setWebAppPath("src/main/resources/webapp");
        server.start();
    }

}

