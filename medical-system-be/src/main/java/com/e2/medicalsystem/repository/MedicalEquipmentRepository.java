package com.e2.medicalsystem.repository;

import com.e2.medicalsystem.model.MedicalEquipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalEquipmentRepository extends JpaRepository<MedicalEquipment, Integer> {
    List<MedicalEquipment> findAllByCompanyId(long companyId);
}
