package com.e2.medicalsystem.repository;

import com.e2.medicalsystem.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {
    Contract findByCompanyName(String companyName);
    Contract findByUsername(String username);
    Contract findByUsernameAndCompanyName(String username, String companyName);
    @Query("SELECT c FROM Contract c WHERE DAY(c.startDate) = :dayOfMonth")
    List<Contract> findByStartDateDay(@Param("dayOfMonth") int dayOfMonth);
}
