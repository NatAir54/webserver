package com.studying.webserver.io;

import lombok.AllArgsConstructor;
import java.io.BufferedWriter;
import java.io.IOException;


@AllArgsConstructor
public class ResponseWriter {
    BufferedWriter writer;

    void writeSuccessResponse(String content) throws IOException {
        writer.write("HTTP/1.1 200 OK");
        writer.newLine();
        writer.newLine();
        writer.write(content);
        writer.flush();
    }

    void writeBadRequestResponse(String message) throws IOException {
        writer.write("HTTP/1.1 400 " + message);
        writer.newLine();
        writer.newLine();
    }

    void writeMethodNotAllowedResponse(String message) throws IOException {
        writer.write("HTTP/1.1 405 " + message);
        writer.newLine();
        writer.newLine();
    }

    void writeFileNotFoundResponse(String message) throws IOException {
        writer.write("HTTP/1.1 404 " + message);
        writer.newLine();
        writer.newLine();
    }
}
