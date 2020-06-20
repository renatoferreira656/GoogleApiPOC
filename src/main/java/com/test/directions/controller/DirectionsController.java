package com.test.directions.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/directions")
public class DirectionsController {

    @GetMapping("/coordinates")
    public String healthCheck(@RequestParam(value="location") String location) {
        return location;
    }

}
