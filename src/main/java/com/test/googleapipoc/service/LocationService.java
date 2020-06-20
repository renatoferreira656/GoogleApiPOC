package com.test.googleapipoc.service;

import com.test.googleapipoc.model.Location;
import com.test.googleapipoc.model.geocode.GeoCodeInfo;
import com.test.googleapipoc.model.geocode.GeoCodeResults;
import com.test.googleapipoc.model.geocode.GeoPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class LocationService {

    @Value("${spring.google.api.key}")
    private String googleApiKey;

    @Autowired
    private HTTPService httpService;

    public List<Location> findCoordinatesBy(String location) {
        Map<String, String> query = new HashMap<>();
        query.put("address", location);
        query.put("key", googleApiKey);
        List<GeoCodeInfo> result = httpService.get(GeoCodeResults.class, "https://maps.googleapis.com/maps/api/geocode/json", query).getResults();
        if(result==null || result.isEmpty()){
            return Collections.singletonList(Location.notFound(location));
        }
        return result.stream().map(geoCodeInfo -> {
            GeoPoint geoPoint = geoCodeInfo.getGeometry().getLocation();
            String formattedAddress = geoCodeInfo.getFormattedAddress();
            return Location.found(location, formattedAddress, geoPoint);
        }).collect(Collectors.toList());
    }

    public List<Location> findCoordinatesBy(Set<String> locations) {
        return locations.stream().map(this::findCoordinatesBy).reduce(new ArrayList<>(), (a, b) -> {
            a.addAll(b);
            return a;
        });
    }

    @Autowired
    public void setHTTPService(HTTPService httpService) {
        this.httpService = httpService;
    }

}
