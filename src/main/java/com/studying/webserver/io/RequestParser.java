package com.studying.webserver.io;

import com.studying.webserver.exceptions.BadRequestException;
import com.studying.webserver.exceptions.MethodNotAllowedException;
import lombok.AllArgsConstructor;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@AllArgsConstructor
public class RequestParser {
    BufferedReader reader;

    HttpRequest parseRequest() throws IOException {
        HttpRequest request = new HttpRequest();
        String firstLine = reader.readLine();
        injectUriAndMethod(firstLine, request);
        injectHeaders(reader, request);
        return request;
    }

    static void injectUriAndMethod(String line, HttpRequest request) {
        if (Objects.isNull(line)) {
            throw new BadRequestException("Null request received");
        }

        String uri = line.substring(line.indexOf("/"), line.lastIndexOf(" HTTP"));
        request.setUri(uri);

        String method = line.substring(0, line.indexOf(" "));
        HttpMethod httpMethod = HttpMethod.valueOf(method);

        if (httpMethod == HttpMethod.POST) {
            throw new MethodNotAllowedException("Method " + httpMethod + " is not allowed");
        }
        request.setMethod(httpMethod);
    }


    static void injectHeaders(BufferedReader reader, HttpRequest request) throws IOException {
        Map<String, String> headersMap = new HashMap<>();
        while(true) {
            String line = reader.readLine();
            if (Objects.isNull(line) || line.isBlank()) {
                break;
            }
            int separator = line.indexOf(':');
            headersMap.put(line.substring(0, separator),
                    line.substring(separator + 1));
        }
        request.setHeaders(headersMap);
    }
}
