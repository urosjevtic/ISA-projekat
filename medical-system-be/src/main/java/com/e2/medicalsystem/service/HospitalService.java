package com.e2.medicalsystem.service;

import com.e2.medicalsystem.model.Hospital;
import com.e2.medicalsystem.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository)
    {
        this.hospitalRepository = hospitalRepository;
    }

    public Hospital saveHospital(Hospital hospital)
    {
        return hospitalRepository.save(hospital);
    }
}
