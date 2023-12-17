package com.e2.medicalsystem.controller;

import com.e2.medicalsystem.model.MedicalEquipment;
import com.e2.medicalsystem.service.MedicalEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    public void deleteEquipmentById(@PathVariable int id) {
        medicalEquipmentService.deleteEquipmentById(id);
    }
}
