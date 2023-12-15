package com.e2.medicalsystem.service.impl;

import com.e2.medicalsystem.dto.ReservationDto;
import com.e2.medicalsystem.model.Reservation;
import com.e2.medicalsystem.model.ReservationItem;
import com.e2.medicalsystem.repository.AppointmentRepository;
import com.e2.medicalsystem.repository.ReservationItemRepository;
import com.e2.medicalsystem.repository.ReservationRepository;
import com.e2.medicalsystem.service.ReservationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        reservation.setAppointment(appointmentRepository.getById(reservationDto.getAppointment().getId()));
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

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.getById(id);
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
