package com.e2.medicalsystem.service;

import com.e2.medicalsystem.model.Location;
import com.e2.medicalsystem.repository.LocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository)
    {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocation()
    {
        return locationRepository.findAll();
    }

    public Location getById(Integer id)
    {
        return locationRepository.findById(id).orElse(null);
    }
}
