package com.e2.medicalsystem.service;

import com.e2.medicalsystem.dto.ProducerDto;
import com.e2.medicalsystem.model.Contract;

import java.util.List;

public interface ContractService {

    public List<Contract> getAllContracts();
    public Contract saveContract(Contract contract);
    void updateContract(Contract contract);
    public Contract findByCompanyName(String companyName);
    public Contract findByUsername(String username);
    public Contract findByUsernameAndCompanyName(String username, String companyName);
    void sendContract(ProducerDto producerDto);

}
