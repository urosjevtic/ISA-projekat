package com.e2.medicalsystem.service.impl;

import com.e2.medicalsystem.model.ReservationItem;
import com.e2.medicalsystem.repository.ReservationItemRepository;
import com.e2.medicalsystem.repository.ReservationRepository;
import com.e2.medicalsystem.service.ReservationItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationItemServiceImpl implements ReservationItemService {
    private final ReservationItemRepository reservationItemRepository;

    @Autowired
    public ReservationItemServiceImpl(ReservationItemRepository reservationItemRepository){
        this.reservationItemRepository = reservationItemRepository;
    }



    @Override
    public ReservationItem save(ReservationItem reservationItem) {
        return reservationItemRepository.save(reservationItem);
    }

    @Override
    public ReservationItem getById(Long id) {
        return reservationItemRepository.getById(id);
    }
}
