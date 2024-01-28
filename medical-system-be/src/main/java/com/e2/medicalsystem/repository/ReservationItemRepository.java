package com.e2.medicalsystem.repository;

import com.e2.medicalsystem.model.Reservation;
import com.e2.medicalsystem.model.ReservationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationItemRepository extends JpaRepository<ReservationItem, Long> {
}
