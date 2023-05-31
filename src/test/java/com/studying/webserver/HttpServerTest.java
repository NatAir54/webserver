package com.studying.webserver;

import com.studying.webserver.server.HttpServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HttpServerTest {
    private HttpServer server;

    @BeforeEach
    public void before() {
        server = new HttpServer();
    }

     @Test
    public void setServerPort() {

    }


}
