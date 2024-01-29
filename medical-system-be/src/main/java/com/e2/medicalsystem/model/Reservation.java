package com.e2.medicalsystem.model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Appointment appointment;
    @Column(name = "reservationItems")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ReservationItem> reservationItems;
    @Column(name = "reserverId")
    private Long reserverId;

    @Column(columnDefinition = "boolean default false")
    private boolean isDelivered;

    @Version
    private Long version;
    public Reservation(){}

    public Reservation(Long id, Appointment appointment, List<ReservationItem> reservationItems, Long reserverId,boolean isDelivered) {
        this.id = id;
        this.appointment = appointment;
        this.reservationItems = reservationItems;
        this.reserverId = reserverId;
        this.isDelivered = isDelivered;
    }

    public Reservation(Appointment appointment, List<ReservationItem> reservationItems, Long reserverId,boolean isDelivered) {
        this.appointment = appointment;
        this.reservationItems = reservationItems;
        this.reserverId = reserverId;
        this.isDelivered = isDelivered;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public List<ReservationItem> getReservationItems() {
        return reservationItems;
    }

    public void setReservationItems(List<ReservationItem> reservationItems) {
        this.reservationItems = reservationItems;
    }

    public Long getReserverId() {
        return reserverId;
    }

    public void setReserverId(Long reserverId) {
        this.reserverId = reserverId;
    }
}
