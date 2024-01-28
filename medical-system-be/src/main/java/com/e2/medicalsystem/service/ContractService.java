package com.e2.medicalsystem.service;

import com.e2.medicalsystem.model.Contract;

public interface ContractService {
    public Contract saveContract(Contract contract);
    void updateContract(Contract contract);
    public Contract findByCompanyName(String companyName);

}
