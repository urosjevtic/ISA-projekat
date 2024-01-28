package com.e2.medicalsystem.repository;

import com.e2.medicalsystem.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {
    Contract findByCompanyName(String companyName);
}
