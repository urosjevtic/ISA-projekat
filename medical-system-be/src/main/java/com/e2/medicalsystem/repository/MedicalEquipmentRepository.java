package com.e2.medicalsystem.repository;

import com.e2.medicalsystem.model.MedicalEquipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalEquipmentRepository extends JpaRepository<MedicalEquipment, Integer> {
}
