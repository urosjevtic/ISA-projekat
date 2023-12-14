package com.e2.medicalsystem.service;

import com.e2.medicalsystem.model.Appointment;

import java.util.List;

public interface AppointmentService {
    public void saveAppointment(Appointment appointment);
    public List<Appointment> getAllAppointmentsByCompanyId(long companyId);
}
