package com.e2.medicalsystem.service;

import com.e2.medicalsystem.dto.LatLng;

import java.util.List;

public interface SimulatorService {
    public void StartSimulator(List<LatLng> coordinates,String forUser,String refreshRate);
}
