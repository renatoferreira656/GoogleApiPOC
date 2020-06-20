package com.test.directions.helper;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class URLHelperTest {

    @Test
    public void testShouldReturnAllLinesFromFile(){
        String value = URLHelper.formatURL("http://url", Collections.singletonMap("key", "value"));
        assertEquals("http://url?key=value", value);

        value = URLHelper.formatURL("http://url", Collections.singletonMap("key", "value value"));
        assertEquals("http://url?key=value value", value);

        value = URLHelper.formatURL("http://url", null);
        assertEquals("http://url", value);

        value = URLHelper.formatURL("http://url", Collections.emptyMap());
        assertEquals("http://url", value);
    }
}
