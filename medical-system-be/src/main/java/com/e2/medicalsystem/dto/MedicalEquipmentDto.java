package com.e2.medicalsystem.dto;

import com.e2.medicalsystem.model.MedicalEquipment;

public class MedicalEquipmentDto {
    private Integer id;
    private String name;
    private String description;
    private long companyId;

    public MedicalEquipmentDto(){}
    public MedicalEquipmentDto(Integer id, String name, String description, long companyId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.companyId = companyId;
    }

    public MedicalEquipmentDto(MedicalEquipment medicalEquipment)
    {
        this(medicalEquipment.getId(), medicalEquipment.getName(), medicalEquipment.getDescription(), medicalEquipment.getCompanyId());
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
