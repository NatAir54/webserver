package com.studying.webserver;

import lombok.AllArgsConstructor;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;


@AllArgsConstructor
public class RequestParser {
    BufferedReader reader;

    HttpRequest parseRequest() throws IOException {
        HttpRequest request = new HttpRequest();
        String firstLine = "";

        int counter = 0;
        while (true) {
            String str = reader.readLine();
            System.out.println(str);
            if (str.equals("END")) {
                break;
            }
//            counter++;
//            if (counter == 1) {
//                firstLine = str;
//            }

            StringTokenizer parse = new StringTokenizer(str);
            String method = parse.nextToken().toUpperCase();
            String fileRequested = parse.nextToken().toLowerCase();
            request.setMethod(HttpMethod.valueOf(method));
            request.setUri(fileRequested);
        }
        //injectUriAndMethod(firstLine, request);

        return request;
    }

    private static void injectUriAndMethod(String line, HttpRequest request){
        String[] firstLine = line.split(" ", 3);
        request.setMethod(HttpMethod.valueOf(firstLine[0]));
        request.setUri(firstLine[1]);

//        String uri = line.substring(line.indexOf(' ') + 1, line.lastIndexOf(' '));
//        String method = line.substring(0, line.indexOf('/'));
//        request.setMethod(HttpMethod.valueOf(method));
//        request.setUri(uri);
    }

    private static void injectHeaders(BufferedReader reader, HttpRequest request) {

    }
}

// GET /index.html

// uri = /index.html
// webAppPath = src/main/resources/webapp

// path to resource = webAppPath + uri => src/main/resources/webapp/index.html => read with FileInputStream

// ====================================================================

// GET /css/style.css
// uri = /css/style.css
// webAppPath = src/main/resources/webapp

// path to resource = webAppPath + uri => src/main/resources/webapp/css/style.css => read with FileInputStream

// =============================

// GET uri
// response -> webAppPath + uri
