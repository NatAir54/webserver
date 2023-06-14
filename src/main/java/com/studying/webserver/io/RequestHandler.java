package com.studying.webserver.io;

import com.studying.webserver.exceptions.BadRequestException;
import com.studying.webserver.exceptions.MethodNotAllowedException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.Map;


@AllArgsConstructor
public class RequestHandler {
    BufferedReader input;
    OutputStream output;
    private String webAppPath;


    public void handle() throws IOException {
        RequestParser requestParser = new RequestParser(input);
        ResourceReader resourceReader = new ResourceReader(webAppPath);
        ResponseWriter responseWriter = new ResponseWriter(output);

        try {
            HttpRequest request = requestParser.parseRequest();
            InputStream content = resourceReader.readResource(request.getUri());
            responseWriter.writeSuccessResponse(content);

        } catch (BadRequestException e) {
            responseWriter.writeBadRequestResponse(e.getMessage());
        } catch (MethodNotAllowedException e) {
            responseWriter.writeMethodNotAllowedResponse(e.getMessage());
        } catch (FileNotFoundException e) {
            responseWriter.writeFileNotFoundResponse(e.getMessage());
        } catch (RuntimeException e) {
            responseWriter.writeSomethingIsWrong(e.getMessage());
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
