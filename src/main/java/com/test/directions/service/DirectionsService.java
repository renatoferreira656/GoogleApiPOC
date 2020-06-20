package com.test.directions.service;

import com.test.directions.json.Json;
import com.test.directions.model.geocode.GeoCodeResults;
import com.test.directions.model.geocode.GeoPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DirectionsService {

    @Value("${spring.google.api.key}")
    private String googleApiKey;

    @Autowired
    private HTTPService httpService;

    public GeoPoint findCoordinatesBy(String location) {
        Map<String, String> query = new HashMap<>();
        query.put("address", location);
        query.put("key", googleApiKey);
        GeoCodeResults json = httpService.get(GeoCodeResults.class, "https://maps.googleapis.com/maps/api/geocode/json", query);
        if(json.getResults().isEmpty()){
            return null;
        }
        return json.getResults().get(0).getGeometry().getLocation();
    }

    @Autowired
    public void setHTTPService(HTTPService httpService) {
        this.httpService = httpService;
    }

}
