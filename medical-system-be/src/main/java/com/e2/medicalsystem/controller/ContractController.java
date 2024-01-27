package com.e2.medicalsystem.controller;

import com.e2.medicalsystem.model.Contract;
import com.e2.medicalsystem.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
