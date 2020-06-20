package com.test.directions.controller;

import com.test.directions.helper.FilesHelper;
import com.test.directions.http.BadRequestException;
import com.test.directions.http.NotFoundException;
import com.test.directions.model.Location;
import com.test.directions.service.DirectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/directions")
public class DirectionsController {

    @Autowired
    private DirectionsService directionsService;

    @GetMapping("/coordinates")
    public List<Location>  coordinates(@RequestParam(value="location") String location) {
        List<Location> positions = this.directionsService.findCoordinatesBy(location);
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

        List<Location> positions = this.directionsService.findCoordinatesBy(locations);
        if(positions == null || positions.isEmpty()){
            return null;
        }
        return positions;
    }

    @Autowired
    public void setDirectionsService(DirectionsService directionsService) {
        this.directionsService = directionsService;
    }
}
