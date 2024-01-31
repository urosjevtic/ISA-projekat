package com.e2.medicalsystem.service;

import com.e2.medicalsystem.dto.ReservationDto;
import com.e2.medicalsystem.model.Reservation;

import java.util.List;

public interface ReservationService {
    public List<Reservation> getAllReservations();
    public Reservation saveReservation(ReservationDto reservationDto);
    public Reservation getReservationById(Long id);
    public void deleteReservation(Long id);
    public List<Reservation> getAllReservationsByReserverId(Long reserverId);
    public ReservationDto cancelReservation(Long id, Long userId);
    void finishDelivery(Long id);
}
