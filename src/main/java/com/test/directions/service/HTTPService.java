package com.test.directions.service;

import com.test.directions.helper.URLHelper;
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
        String urlWithParams = URLHelper.formatURL(url, params);
        ResponseEntity<T> forEntity = restTemplate.getForEntity(urlWithParams, clazz);
        return forEntity.getBody();
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
