package com.e2.medicalsystem.service.impl;

import com.e2.medicalsystem.model.Location;
import com.e2.medicalsystem.repository.LocationRepository;

import com.e2.medicalsystem.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;


    public List<Location> getAllLocation()
    {
        return locationRepository.findAll();
    }

    public Location getById(Integer id)
    {
        return locationRepository.findById(id).orElse(null);
    }
}
