package com.e2.medicalsystem.dto;

import com.e2.medicalsystem.model.Reservation;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationDto {
    private Long id;

    private AppointmentDto appointment;
    private List<ReservationItemDto> reservationItems;
    private Long reserverId;

    public ReservationDto(){}
    public ReservationDto(Reservation reservation){
        this.id = reservation.getId();
        this.appointment = new AppointmentDto(reservation.getAppointment());
        this.reserverId = reservation.getReserverId();
        for (var item:
             reservation.getReservationItems()) {
            this.reservationItems.add(new ReservationItemDto(item));
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppointmentDto getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentDto appointment) {
        this.appointment = appointment;
    }

    public List<ReservationItemDto> getReservationItems() {
        return reservationItems;
    }

    public void setReservationItems(List<ReservationItemDto> reservationItems) {
        this.reservationItems = reservationItems;
    }

    public Long getReserverId() {
        return reserverId;
    }

    public void setReserverId(Long reserverId) {
        this.reserverId = reserverId;
    }
}
