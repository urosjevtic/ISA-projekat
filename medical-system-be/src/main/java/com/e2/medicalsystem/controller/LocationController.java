package com.e2.medicalsystem.controller;

import com.e2.medicalsystem.service.LocationService;
import com.e2.medicalsystem.service.impl.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "location")
public class LocationController {

    @Autowired
    private LocationService locationServiceImpl;

}
