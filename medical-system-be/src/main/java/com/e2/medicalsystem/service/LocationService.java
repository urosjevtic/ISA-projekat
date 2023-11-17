package com.e2.medicalsystem.service;

import com.e2.medicalsystem.model.Location;

import java.util.List;

public interface LocationService {
    public List<Location> getAllLocation();
    public Location getById(Integer id);
}
