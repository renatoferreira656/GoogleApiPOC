package com.test.directions.helper;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class URLHelper {

    public static String formatURL(String url, Map<String, String> params) {
        final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        if(params != null) params.forEach(uriBuilder::queryParam);
        return uriBuilder.build(false).toUriString();
    }
}
