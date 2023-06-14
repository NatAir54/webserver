package com.studying.webserver.io;

import lombok.AllArgsConstructor;

import java.io.*;


@AllArgsConstructor
public class ResourceReader {
    String webAppPath;

    String readResource(String uri) throws IOException {
        File file = new File(webAppPath, uri);
        if (!file.exists() || file.isDirectory()) {
            throw new FileNotFoundException("File " + file.getPath() + " not found");
        }

        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.ready()) {
                content.append(br.readLine());
            }
        }

        return content.toString();
    }
}
