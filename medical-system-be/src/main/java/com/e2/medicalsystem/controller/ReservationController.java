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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasAuthority('ROLL_USER')")
    public ResponseEntity<String> saveReservation(@RequestBody ReservationDto reservationDto) {
        reservationService.saveReservation(reservationDto);
        return new ResponseEntity<>("Reservation saved successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/userReservation")
    @PreAuthorize("hasAuthority('ROLL_USER')")
    public ResponseEntity<List<ReservationDto>> getUserReservation(@RequestParam long userId){
        List<Reservation> allReservations = reservationService.getAllReservationsByReserverId(userId);
        List<ReservationDto> allReservationsDto = new ArrayList<>();
        for (var res:
             allReservations) {
            allReservationsDto.add(new ReservationDto(res));
        }

        return new ResponseEntity<>(allReservationsDto, HttpStatus.OK);
    }
}
