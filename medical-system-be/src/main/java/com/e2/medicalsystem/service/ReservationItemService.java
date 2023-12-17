package com.e2.medicalsystem.service;

import com.e2.medicalsystem.model.Reservation;
import com.e2.medicalsystem.model.ReservationItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationItemService {
    public ReservationItem save(ReservationItem reservationItem);
    public ReservationItem getById(Long id);
}
