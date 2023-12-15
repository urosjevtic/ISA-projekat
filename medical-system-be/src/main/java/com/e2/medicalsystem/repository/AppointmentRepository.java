package com.e2.medicalsystem.repository;

import com.e2.medicalsystem.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByCompanyId(long companyId);
    List<Appointment> findAllByCompanyIdAndTakenFalse(long companyId);
}
