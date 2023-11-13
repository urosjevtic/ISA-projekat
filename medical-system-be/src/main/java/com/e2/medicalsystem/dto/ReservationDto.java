package com.e2.medicalsystem.dto;

import com.e2.medicalsystem.model.Reservation;

import java.util.Date;

public class ReservationDto {
    private Long id;
    private Long companyId;
    private Date date;

    public ReservationDto(Long id, Long companyId, Date date) {
        this.id = id;
        this.companyId = companyId;
        this.date = date;
    }

    public ReservationDto(Reservation reservation)
    {
        this(reservation.getId(), reservation.getCompanyId(), reservation.getDate());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
