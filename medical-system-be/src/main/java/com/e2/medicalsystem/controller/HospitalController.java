package com.e2.medicalsystem.controller;

import com.e2.medicalsystem.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/hospitals")
public class HospitalController {
    private final HospitalService hospitalService;
    @Autowired
    public HospitalController(HospitalService hospitalService)
    {
        this.hospitalService = hospitalService;
    }
}
