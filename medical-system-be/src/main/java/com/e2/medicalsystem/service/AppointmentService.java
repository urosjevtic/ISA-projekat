package com.e2.medicalsystem.service;

import com.e2.medicalsystem.model.Appointment;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {
    public Appointment saveAppointment(Appointment appointment);
    public List<Appointment> getAllAppointmentsByCompanyId(long companyId);
    public Appointment getById(Long id);
    public List<Appointment> getAllFreeAppointmentsByCompanyId(long companyId);

    public List<Appointment> getFreeAppointments(long companyId, LocalDate forDate);


}
