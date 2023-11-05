package com.e2.medicalsystem.controller;

import com.e2.medicalsystem.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "location")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService)
    {
        this.locationService = locationService;
    }
}
