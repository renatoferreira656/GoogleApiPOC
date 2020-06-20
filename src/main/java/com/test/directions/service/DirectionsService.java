package com.test.directions.service;

import com.test.directions.model.Location;
import com.test.directions.model.geocode.GeoCodeInfo;
import com.test.directions.model.geocode.GeoCodeResults;
import com.test.directions.model.geocode.GeoPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DirectionsService {

    @Value("${spring.google.api.key}")
    private String googleApiKey;

    @Autowired
    private HTTPService httpService;

    public Location findCoordinatesBy(String location) {
        Map<String, String> query = new HashMap<>();
        query.put("address", location);
        query.put("key", googleApiKey);
        GeoCodeResults json = httpService.get(GeoCodeResults.class, "https://maps.googleapis.com/maps/api/geocode/json", query);
        if(json.getResults().isEmpty()){
            return Location.notFound(location);
        }
        GeoCodeInfo geoCodeInfo = json.getResults().get(0);
        GeoPoint geoPoint = geoCodeInfo.getGeometry().getLocation();
        String formattedAddress = geoCodeInfo.getFormattedAddress();
        return Location.found(location, formattedAddress, geoPoint);
    }

    public List<Location> findCoordinatesBy(Set<String> locations) {
        return locations.stream().map(this::findCoordinatesBy).collect(Collectors.toList());
    }

    @Autowired
    public void setHTTPService(HTTPService httpService) {
        this.httpService = httpService;
    }

}
