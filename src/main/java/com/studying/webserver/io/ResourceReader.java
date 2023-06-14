package com.studying.webserver.io;

import lombok.AllArgsConstructor;

import java.io.*;


@AllArgsConstructor
public class ResourceReader {
    String webAppPath;

    InputStream readResource(String uri) throws IOException {
        File file = new File(webAppPath, uri);
        if (!file.exists() || file.isDirectory()) {
            throw new FileNotFoundException("File " + file.getPath() + " not found");
        }

        return new FileInputStream(file);
    }
}
