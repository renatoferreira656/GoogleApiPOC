package com.test.googleapipoc.controller;

import com.test.googleapipoc.helper.FilesHelper;
import com.test.googleapipoc.http.BadRequestException;
import com.test.googleapipoc.http.NotFoundException;
import com.test.googleapipoc.model.Location;
import com.test.googleapipoc.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/coordinates")
    public List<Location>  coordinates(@RequestParam(value="location") String location) {
        List<Location> positions = this.locationService.findCoordinatesBy(location);
        if(positions == null || positions.isEmpty()){
            throw new NotFoundException("Not found");
        }
        return positions;
    }

    @PostMapping("/coordinates/batch")
    public List<Location> coordinates(@RequestParam("file") MultipartFile file) {
        Set<String> locations = FilesHelper.readAllFile(file);
        if(locations == null || locations.isEmpty()){
            throw new BadRequestException("file is empty or invalid");
        }

        List<Location> positions = this.locationService.findCoordinatesBy(locations);
        if(positions == null || positions.isEmpty()){
            throw new NotFoundException("Not found");
        }
        return positions;
    }

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }
}
