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

    @Column(name = "canceled")
    private boolean canceled;
    public Reservation(){}

    public Reservation(Long id, Appointment appointment, List<ReservationItem> reservationItems, Long reserverId,boolean isDelivered, boolean canceled) {
        this.id = id;
        this.appointment = appointment;
        this.reservationItems = reservationItems;
        this.reserverId = reserverId;
        this.isDelivered = isDelivered;
        this.canceled = canceled;
    }

    public Reservation(Appointment appointment, List<ReservationItem> reservationItems, Long reserverId,boolean isDelivered, boolean canceled) {
        this.appointment = appointment;
        this.reservationItems = reservationItems;
        this.reserverId = reserverId;
        this.isDelivered = isDelivered;
        this.canceled = canceled;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
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
