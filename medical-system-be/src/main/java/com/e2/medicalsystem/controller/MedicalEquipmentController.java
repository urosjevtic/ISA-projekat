package com.e2.medicalsystem.controller;

import com.e2.medicalsystem.model.MedicalEquipment;
import com.e2.medicalsystem.service.MedicalEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/equipment")
public class MedicalEquipmentController {

    private MedicalEquipmentService medicalEquipmentService;
    @Autowired
    public MedicalEquipmentController(MedicalEquipmentService medicalEquipmentService)
    {
        this.medicalEquipmentService = medicalEquipmentService;
    }

    @GetMapping("/all")
    public List<MedicalEquipment> getAllEquipmentByCompanyId(@RequestParam long companyId) {
        return medicalEquipmentService.getAllEquipmentByCompanyId(companyId);
    }
}
