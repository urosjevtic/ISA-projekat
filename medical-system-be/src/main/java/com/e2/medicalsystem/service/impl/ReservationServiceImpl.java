package com.e2.medicalsystem.service.impl;

import com.e2.medicalsystem.dto.ReservationDto;
import com.e2.medicalsystem.model.Appointment;
import com.e2.medicalsystem.model.Reservation;
import com.e2.medicalsystem.model.ReservationItem;
import com.e2.medicalsystem.repository.AppointmentRepository;
import com.e2.medicalsystem.repository.ReservationItemRepository;
import com.e2.medicalsystem.repository.ReservationRepository;
import com.e2.medicalsystem.service.ReservationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationItemRepository reservationItemRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationItemRepository reservationItemRepository, AppointmentRepository appointmentRepository)
    {
        this.reservationRepository = reservationRepository;
        this.reservationItemRepository = reservationItemRepository;
        this.appointmentRepository = appointmentRepository;
    }


    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Transactional
    @Override
    public Reservation saveReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        Appointment appointment = appointmentRepository.getById(reservationDto.getAppointment().getId());
        if(appointment.isTaken()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Termin already occupied");
        }
        appointment.setTaken(true);
        appointmentRepository.save(appointment);
        reservation.setAppointment(appointment);
        List<ReservationItem> reservationItems = new ArrayList<>();
        for (var item:
                reservationDto.getReservationItems()) {
            ReservationItem reservationItem = new ReservationItem(item);
            reservationItemRepository.save(reservationItem);
            reservationItems.add(reservationItem);
        }
        reservation.setReservationItems(reservationItems);
        reservation.setReserverId(reservationDto.getReserverId());
        return reservationRepository.save(reservation);
    }
    @Transactional
    @Override
    public Reservation getReservationById(Long id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        Reservation reservation = optionalReservation.orElseThrow(() -> new RuntimeException("Reservation not present"));
        return reservation;
    }

    @Override
    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.getById(id);
        reservationRepository.delete(reservation);
    }

    @Override
    public List<Reservation> getAllReservationsByReserverId(Long reserverId){
        return reservationRepository.findAllByReserverId(reserverId);
    }
}
