package com.e2.medicalsystem.controller;

import com.e2.medicalsystem.model.Appointment;
import com.e2.medicalsystem.model.MedicalEquipment;
import com.e2.medicalsystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/appointment")
public class AppointmentController {

    private AppointmentService appointmentService;
    @Autowired
    public AppointmentController(AppointmentService appointmentService)
    {
        this.appointmentService = appointmentService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<String> saveAppointment(@RequestBody Appointment appointment) {
        try{
            appointmentService.saveAppointment(appointment);
            return new ResponseEntity<>("Appointment saved successfully", HttpStatus.OK);
        }catch (RuntimeException ex){
            return new ResponseEntity<>("Appointment already exist", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/all")
    public List<Appointment> getAllAppointmentsByCompanyId(@RequestParam long companyId) {
        return appointmentService.getAllAppointmentsByCompanyId(companyId);
    }

    @GetMapping("/allFree")
    public List<Appointment> getAllFreeAppointmentsByCompanyId(@RequestParam long companyId) {
        return appointmentService.getAllFreeAppointmentsByCompanyId(companyId);
    }

    @GetMapping("/free")
    public List<Appointment> getFreeAppointmentsForDate(@RequestParam long companyId, @RequestParam LocalDate date){
        return appointmentService.getFreeAppointments(companyId,date);
    }
}
