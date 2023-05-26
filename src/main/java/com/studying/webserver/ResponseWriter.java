package com.studying.webserver;

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

    void writeNotSuccessResponse() throws IOException {
        writer.write("HTTP/1.1 404 NOT FOUND");
        writer.newLine();
        writer.newLine();
    }

    void writeBadRequestResponse() throws IOException {
        writer.write("HTTP/1.1 404 BAD REQUEST");
        writer.newLine();
        writer.newLine();
    }


}
