package com.studying.webserver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.Map;


@AllArgsConstructor
public class RequestHandler {
    BufferedReader input;
    BufferedWriter output;
    private String webAppPath;


    public void handle() throws IOException {
        RequestParser requestParser = new RequestParser(input);
        ResourceReader resourceReader = new ResourceReader(webAppPath);
        ResponseWriter responseWriter = new ResponseWriter(output);

        try {
            HttpRequest request = requestParser.parseRequest();
            // if(!(request.getMethod() == HttpMethod.GET)) {
               //
            //}
            String content = resourceReader.readResource(request.getUri());
            responseWriter.writeSuccessResponse(content);
        } catch (FileNotFoundException e) {
            responseWriter.writeNotSuccessResponse();
        }catch(Exception e) {
            responseWriter.writeBadRequestResponse();
        }
    }

}

@Data
@NoArgsConstructor
class HttpRequest {
    private String uri;
    private HttpMethod method;
    private Map<String, String> headers;
}

enum HttpMethod {
    GET, POST
}
