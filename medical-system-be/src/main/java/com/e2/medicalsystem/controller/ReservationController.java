package com.e2.medicalsystem.controller;

import com.e2.medicalsystem.model.Reservation;
import com.e2.medicalsystem.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/reservation")
public class ReservationController {
    private ReservationService reservationService;
    @Autowired
    public ReservationController(ReservationService reservationService)
    {
        this.reservationService = reservationService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<String> saveReservation(@RequestBody Reservation reservation) {
        reservationService.saveReservation(reservation);
        return new ResponseEntity<>("Reservation saved successfully", HttpStatus.OK);
    }
}
