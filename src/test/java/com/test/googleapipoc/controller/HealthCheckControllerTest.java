package com.test.googleapipoc.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthCheckControllerTest {

    @Test
    public void testHealthCheck(){
        String result = new HealthCheckController().healthCheck();
        assertEquals(result, "{\"working\": \"true\" }");

    }
}
