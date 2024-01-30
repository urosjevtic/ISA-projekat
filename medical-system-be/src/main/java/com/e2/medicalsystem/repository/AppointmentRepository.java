package com.e2.medicalsystem.repository;

import com.e2.medicalsystem.model.Appointment;
import com.e2.medicalsystem.model.MedicalEquipment;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByCompanyId(long companyId);
    List<Appointment> findAllByCompanyIdAndTakenFalse(long companyId);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT a FROM Appointment a WHERE a.id = :id")
    Appointment getByIdWithOptimisticLocking(@Param("id") int id);
}
