package com.e2.medicalsystem.controller;

import com.e2.medicalsystem.dto.CompanyProfileDto;
import com.e2.medicalsystem.dto.ContractDto;
import com.e2.medicalsystem.model.CompanyProfile;
import com.e2.medicalsystem.model.Contract;
import com.e2.medicalsystem.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/contract")
public class ContractController {

    private ContractService contractService;
    @Autowired
    public ContractController(ContractService contractService)
    {
        this.contractService = contractService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<String> saveContract(@RequestBody Contract contract) {
        contractService.saveContract(contract);
        return new ResponseEntity<>("Contract saved successfully", HttpStatus.OK);
    }

    @GetMapping(value = "all")
    public ResponseEntity<List<ContractDto>> getAllContracts() {
        List<Contract> allContracts = contractService.getAllContracts();
        List<ContractDto> allContractsDto = new ArrayList<>();

        for (Contract contract : allContracts) {
            allContractsDto.add(new ContractDto(contract));
        }

        return new ResponseEntity<>(allContractsDto, HttpStatus.OK);
    }

}
