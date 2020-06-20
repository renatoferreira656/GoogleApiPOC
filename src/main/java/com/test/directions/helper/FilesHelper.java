package com.test.directions.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class FilesHelper {

    private static Logger logger = LoggerFactory.getLogger(FilesHelper.class);

    public static Set<String> readAllFile(MultipartFile file) {
        if(file.isEmpty()){
            return null;
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            Set<String> lines = new HashSet<>();
            String line = reader.readLine();
            while(line != null){
                lines.add(line.trim());
                line = reader.readLine();
            }
            return lines;
        } catch (IOException e) {
            logger.error("Error reading file", e);
        }
        return null;
    }
}
