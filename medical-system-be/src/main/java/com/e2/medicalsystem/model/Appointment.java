package com.e2.medicalsystem.model;

import com.e2.medicalsystem.dto.AppointmentDto;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long companyId;
    private Long adminId;
    private Date date;
    private int duration;
    private String adminName;
    private String adminLastName;

    public Appointment() {}

    public Appointment(Long id, Long companyId, Long adminId, Date date, int duration, String adminName, String adminLastName) {
        this.id = id;
        this.companyId = companyId;
        this.adminId = adminId;
        this.date = date;
        this.duration = duration;
        this.adminName = adminName;
        this.adminLastName = adminLastName;
    }

    public Appointment(AppointmentDto appointmentDto){
        this.id = appointmentDto.getId();
        this.companyId = appointmentDto.getCompanyId();
        this.adminId = appointmentDto.getAdminId();
        this.date = appointmentDto.getDate();
        this.duration = appointmentDto.getDuration();
        this.adminName = appointmentDto.getAdminName();
        this.adminName = appointmentDto.getAdminLastName();
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

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminLastName() {
        return adminLastName;
    }

    public void setAdminLastName(String adminLastName) {
        this.adminLastName = adminLastName;
    }
}
