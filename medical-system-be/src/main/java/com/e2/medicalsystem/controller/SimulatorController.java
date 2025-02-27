package com.e2.medicalsystem.controller;

import com.e2.medicalsystem.dto.LatLng;
import com.e2.medicalsystem.service.SimulatorService;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/simulator")
public class SimulatorController {

    @Autowired
    private SimulatorService simulatorService;

    @PostMapping(value = "/start")
    public Response StartSimulator(@RequestBody List<LatLng> coordinates, @RequestParam("user") String forUser, @RequestParam("refreshRate") String refreshRate)
    {
        simulatorService.StartSimulator(coordinates,forUser,refreshRate);
        return Response.ok().build();
    }

}
