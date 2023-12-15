package com.e2.medicalsystem.model;

import com.e2.medicalsystem.dto.MedicalEquipmentDto;
import jakarta.persistence.*;

@Entity
public class MedicalEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private long companyId;

    public MedicalEquipment()
    {

    }

    public MedicalEquipment(Integer id, String name, String description, long companyId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.companyId = companyId;
    }

    public MedicalEquipment(MedicalEquipmentDto medicalEquipmentDto){
        this.id = medicalEquipmentDto.getId();
        this.name = medicalEquipmentDto.getName();
        this.description = medicalEquipmentDto.getDescription();
        this.companyId = getCompanyId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }
}
