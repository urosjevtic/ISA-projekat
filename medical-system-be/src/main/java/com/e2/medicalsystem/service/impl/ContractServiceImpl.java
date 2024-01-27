package com.e2.medicalsystem.service.impl;

import com.e2.medicalsystem.model.Contract;
import com.e2.medicalsystem.repository.ContractRepository;
import com.e2.medicalsystem.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    public Contract saveContract(Contract contract) {
        return contractRepository.save(contract);
    }
}
