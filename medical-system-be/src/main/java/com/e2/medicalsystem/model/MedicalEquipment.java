package com.e2.medicalsystem.model;

import com.e2.medicalsystem.dto.MedicalEquipmentDto;
import jakarta.persistence.*;

@Entity
public class MedicalEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private long companyId;
    private int count;

    public MedicalEquipment()
    {

    }

    public MedicalEquipment(int id, String name, String description, long companyId, int count) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.companyId = companyId;
        this.count = count;
    }

    public MedicalEquipment(MedicalEquipmentDto medicalEquipmentDto){
        this.id = medicalEquipmentDto.getId();
        this.name = medicalEquipmentDto.getName();
        this.description = medicalEquipmentDto.getDescription();
        this.companyId = getCompanyId();
        this.count = medicalEquipmentDto.getCount();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
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
