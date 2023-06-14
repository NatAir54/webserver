package com.studying.webserver.io;

import lombok.AllArgsConstructor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


@AllArgsConstructor
public class ResponseWriter {
    OutputStream writer;

    void writeSuccessResponse(InputStream inputStream) throws IOException {
        writer.write("HTTP/1.1 200 OK\n\n".getBytes());
        byte[] buffer = new byte[2051];
        int count;
        while((count = inputStream.read(buffer)) != -1) {
            writer.write(buffer, 0, count);
        }
    }

    void writeBadRequestResponse(String statusText) throws IOException {
        writer.write(("HTTP/1.1 400 " + statusText + "\n\n").getBytes());
    }

    void writeMethodNotAllowedResponse(String statusText) throws IOException {
        writer.write(("HTTP/1.1 405 " + statusText + "\n\n").getBytes());
    }

    void writeFileNotFoundResponse(String statusText) throws IOException {
        writer.write(("HTTP/1.1 404 " + statusText + "\n\n").getBytes());
    }

    void writeSomethingIsWrong(String statusText) throws IOException {
        writer.write(("HTTP/1.1 500 " + statusText + "\n\n").getBytes());
    }
}
