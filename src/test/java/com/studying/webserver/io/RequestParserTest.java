package com.studying.webserver.io;


import com.studying.webserver.exceptions.BadRequestException;
import com.studying.webserver.exceptions.MethodNotAllowedException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RequestParserTest {

    @Test
    void testInjectUriAndMethodSetUriCorrectly () {
        HttpRequest httpRequest = new HttpRequest();
        String testRequest = "GET /test.html HTTP/1.1";
        RequestParser.injectUriAndMethod(testRequest, httpRequest);
        assertEquals("/test.html", httpRequest.getUri());
    }

    @Test
    void testInjectUriAndMethodSetEmptyUriCorrectly () {
        HttpRequest httpRequest = new HttpRequest();
        String testRequest = "GET / HTTP/1.1";
        RequestParser.injectUriAndMethod(testRequest, httpRequest);
        assertEquals("/", httpRequest.getUri());
    }

    @Test
    void testInjectUriAndMethodSetUriPathIncludingFolderCorrectly () {
        HttpRequest httpRequest = new HttpRequest();
        String testRequest = "GET /folder/anotherFolder/test.css HTTP/1.1";
        RequestParser.injectUriAndMethod(testRequest, httpRequest);
        assertEquals("/folder/anotherFolder/test.css", httpRequest.getUri());
    }

    @Test
    void testInjectUriAndMethodSetHttpMethodCorrectly () {
        HttpRequest httpRequest = new HttpRequest();
        String testRequest = "GET /testUrl HTTP/1.1";
        RequestParser.injectUriAndMethod(testRequest, httpRequest);
        assertEquals(HttpMethod.GET, httpRequest.getMethod());
    }


    @Test
    void testInjectUriAndMethodThrowMethodNotAllowedExceptionReceivingPostRequest () {
        HttpRequest httpRequest = new HttpRequest();
        String testRequest = "POST /testUrl HTTP/1.1";
        MethodNotAllowedException thrown = assertThrows(MethodNotAllowedException.class, () ->
                RequestParser.injectUriAndMethod(testRequest, httpRequest), "MethodNotAllowedException was expected");
        assertEquals("Method POST is not allowed", thrown.getMessage());
    }

    @Test
    void testInjectUriAndMethodThrowBasRequestExceptionReceivingNullRequest () {
        HttpRequest httpRequest = new HttpRequest();
        BadRequestException thrown = assertThrows(BadRequestException.class, () ->
            RequestParser.injectUriAndMethod(null, httpRequest), "BadRequestException was expected");
        assertEquals("Null request received", thrown.getMessage());
    }

}
