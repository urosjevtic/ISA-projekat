package com.e2.medicalsystem.dto;

import com.e2.medicalsystem.model.Appointment;
import com.e2.medicalsystem.model.Reservation;

import java.util.Date;

public class AppointmentDto {
    private Long id;
    private Long companyId;
    private Long adminId;
    private Date date;
    private int duration;

    public AppointmentDto(Long id, Long companyId, Long adminId, Date date, int duration) {
        this.id = id;
        this.companyId = companyId;
        this.adminId = adminId;
        this.date = date;
        this.duration = duration;
    }

    public AppointmentDto(Appointment appointment)
    {
        this(appointment.getId(), appointment.getCompanyId(), appointment.getAdminId(), appointment.getDate(), appointment.getDuration());
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

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
