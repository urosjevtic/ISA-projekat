package com.e2.medicalsystem.service.impl;

import com.e2.medicalsystem.model.Appointment;
import com.e2.medicalsystem.repository.AppointmentRepository;
import com.e2.medicalsystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void saveAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointmentsByCompanyId(long companyId) {
        return appointmentRepository.findAllByCompanyId(companyId);
    }

    @Override
    public Appointment getById(Long id) {
        return appointmentRepository.getById(id);
    }
}
