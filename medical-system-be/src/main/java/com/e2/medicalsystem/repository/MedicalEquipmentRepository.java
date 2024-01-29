package com.e2.medicalsystem.repository;

import com.e2.medicalsystem.model.MedicalEquipment;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Isolation;

import java.util.List;

public interface MedicalEquipmentRepository extends JpaRepository<MedicalEquipment, Integer> {
    List<MedicalEquipment> findAllByCompanyId(long companyId);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT m FROM MedicalEquipment m WHERE m.id = :id")
    MedicalEquipment getByIdWithOptimisticLocking(@Param("id") int id);
}
