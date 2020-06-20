package com.test.directions.controller;

import com.test.directions.model.geocode.GeoPoint;
import com.test.directions.service.DirectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/directions")
public class DirectionsController {

    @Autowired
    private DirectionsService directionsService;

    @GetMapping("/coordinates")
    public GeoPoint coordinates(@RequestParam(value="location") String location) {
        return this.directionsService.findCoordinatesBy(location);
    }

    @Autowired
    public void setDirectionsService(DirectionsService directionsService) {
        this.directionsService = directionsService;
    }
}
