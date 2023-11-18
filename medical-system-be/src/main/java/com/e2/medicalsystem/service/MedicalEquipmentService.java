package com.e2.medicalsystem.service;

import com.e2.medicalsystem.model.MedicalEquipment;
import com.e2.medicalsystem.repository.MedicalEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MedicalEquipmentService {

    private final MedicalEquipmentRepository medicalEquipmentRepository;
    @Autowired
    public MedicalEquipmentService(MedicalEquipmentRepository medicalEquipmentRepository) {
        this.medicalEquipmentRepository = medicalEquipmentRepository;
    }

    public List<MedicalEquipment> getAllEquipmentByCompanyId(long companyId) {
        return medicalEquipmentRepository.findAllByCompanyId(companyId);
    }
}
