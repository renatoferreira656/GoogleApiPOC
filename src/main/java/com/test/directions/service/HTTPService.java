package com.test.directions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Component
public class HTTPService {
    @Autowired
    private RestTemplate restTemplate;

    public <T> T get(Class<T> clazz, String url, Map<String, String> params) {
        final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        params.forEach(uriBuilder::queryParam);
        ResponseEntity<T> forEntity = restTemplate.getForEntity(uriBuilder.build(false).toUriString(), clazz);
        return forEntity.getBody();
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
