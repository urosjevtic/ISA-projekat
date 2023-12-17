package com.e2.medicalsystem.controller;

import com.e2.medicalsystem.dto.AppointmentDto;
import com.e2.medicalsystem.dto.ReservationDto;
import com.e2.medicalsystem.model.*;
import com.e2.medicalsystem.service.*;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/reservation")
public class ReservationController {
    private ReservationService reservationService;
    private AppointmentService appointmentService;
    private QrCodeService qrCodeService;
    private EmailSenderService emailSenderService;
    private UsersService usersService;
    @Autowired
    public ReservationController(ReservationService reservationService, AppointmentService appointmentService,
                                 QrCodeService qrCodeService, EmailSenderService emailSenderService, UsersService usersService)
    {
        this.reservationService = reservationService;
        this.appointmentService = appointmentService;
        this.qrCodeService = qrCodeService;
        this.emailSenderService = emailSenderService;
        this.usersService = usersService;
    }


    @PostMapping(value = "/save")
    @PreAuthorize("hasAuthority('ROLL_USER')")
    public ResponseEntity<ReservationDto> saveReservation(@RequestBody ReservationDto reservationDto) {
        ReservationDto reservation = new ReservationDto(reservationService.saveReservation(reservationDto));
        return new ResponseEntity<>(reservation, HttpStatus.OK);
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



    @GetMapping("/generate-and-send-email")
    public ResponseEntity<String> generateQrCodeAndSendEmail(@RequestParam int senderId,
                                                             @RequestParam long reservationId) throws WriterException, IOException
    {
        Reservation reservation = reservationService.getReservationById(reservationId);

        String content = createQrContent(reservation);
        byte[] qrCodeBytes = qrCodeService.generateQrCode(content, 200, 200);

        User user = usersService.getUserById(senderId);
        String email = user.getEmail();

        emailSenderService.sendEmailWithQrCode(email, "Reservation number " + reservation.getId(), content, qrCodeBytes);

        return ResponseEntity.ok("Email sent successfully!");
    }

    private String createQrContent(Reservation reservation) {
        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append("Reservation number: ");
        contentBuilder.append((reservation.getId()));
        contentBuilder.append(", pickup date: ");
        contentBuilder.append(reservation.getAppointment().getDate());
        contentBuilder.append(": \n");
        for (var reservationItem :
                reservation.getReservationItems()) {
            contentBuilder.append("*");
            contentBuilder.append(reservationItem.getEquipment().getName());
            contentBuilder.append("(");
            contentBuilder.append(reservationItem.getCount());
            contentBuilder.append(")\n");
        }
        contentBuilder.append(("\n\n Thank you for ordering"));

        return contentBuilder.toString();
    }

    @PostMapping(value = "/saveCustomReservation")
    public ResponseEntity<ReservationDto> saveCustomReservation(@RequestBody ReservationDto reservationDto){

        AppointmentDto appointmentDto = reservationDto.getAppointment();


        Appointment appointment = new Appointment(appointmentDto.getId(),
                appointmentDto.getCompanyId(),
                appointmentDto.getAdminId(),
                appointmentDto.getDate(),
                appointmentDto.getDuration(),
                appointmentDto.getAdminName(),
                appointmentDto.getAdminLastName(),
                false
        );


        appointment = appointmentService.saveAppointment(appointment);


        appointmentDto.setId(appointment.getId());

        reservationDto.setAppointment(appointmentDto);

        Reservation reservation = reservationService.saveReservation(reservationDto);

        appointment.setTaken(true);
        appointmentService.saveAppointment(appointment);

        reservationDto.setId(reservation.getId());
        return new ResponseEntity<>(reservationDto, HttpStatus.OK);

    }
}
