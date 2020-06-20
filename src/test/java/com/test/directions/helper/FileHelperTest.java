package com.test.directions.helper;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class FileHelperTest {

    @Test
    public void testShouldReturnAllLinesFromFile(){
        String content = "line1\nline2\nline3";
        MultipartFile file = new MockMultipartFile("name", content.getBytes());
        Set<String> lines = FilesHelper.readAllFile(file);

        Set<String> expected = new HashSet<>();
        expected.add("line1");
        expected.add("line2");
        expected.add("line3");
        assertEquals(expected, lines);
    }

    @Test
    public void testShouldReceiveExceptionWhenGetInputStream(){
        String content = "line1\nline2\nline3";
        MultipartFile file = new MockMultipartFile("name", content.getBytes()) {
            @Override
            public InputStream getInputStream() throws IOException {
                throw new IOException("cant read buffer");
            }
        };
        Set<String> lines = FilesHelper.readAllFile(file);
        assertNull(lines);
    }
}
