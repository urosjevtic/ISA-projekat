package com.e2.medicalsystem.controller;

import com.e2.medicalsystem.dto.ReservationDto;
import com.e2.medicalsystem.model.Appointment;
import com.e2.medicalsystem.model.Reservation;
import com.e2.medicalsystem.model.ReservationItem;
import com.e2.medicalsystem.service.AppointmentService;
import com.e2.medicalsystem.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/reservation")
public class ReservationController {
    private ReservationService reservationService;
    private AppointmentService appointmentService;
    @Autowired
    public ReservationController(ReservationService reservationService, AppointmentService appointmentService)
    {
        this.reservationService = reservationService;
        this.appointmentService = appointmentService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<String> saveReservation(@RequestBody ReservationDto reservationDto) {


        reservationService.saveReservation(reservationDto);
        return new ResponseEntity<>("Reservation saved successfully", HttpStatus.OK);
    }
}
