package com.e2.medicalsystem.service.impl;

import com.e2.medicalsystem.dto.ProducerDto;
import com.e2.medicalsystem.model.CompanyProfile;
import com.e2.medicalsystem.model.Contract;
import com.e2.medicalsystem.model.EContractStatus;
import com.e2.medicalsystem.repository.ContractRepository;
import com.e2.medicalsystem.service.ContractService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public List<Contract> getAllContracts(){ return contractRepository.findAll(); }

    @Override
    public Contract saveContract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public void updateContract(Contract contract) {
        contractRepository.save(contract);
    }

    @Override
    public Contract findByCompanyName(String companyName) {
        return contractRepository.findByCompanyName(companyName);
    }

    @Override
    public Contract findByUsername(String username) {
        return contractRepository.findByUsername(username);
    }

    @Override
    public Contract findByUsernameAndCompanyName(String username, String companyName) {
        return contractRepository.findByUsernameAndCompanyName(username, companyName);
    }

    //@Scheduled(cron = "*/5 * * * * *")  // Run every 5 seconds (***ZA JEMTU RADI TESTIRANJA - SLUSA SVAKIH 5 SEKUNDI***)
    @Scheduled(cron = "0 0 0 * * ?")  // Run every day at midnight
    public void checkAndSendContracts() {
        System.out.println("Checking and sending contracts...");
        int currentDayOfMonth = LocalDate.now().getDayOfMonth();
        List<Contract> contracts = contractRepository.findByStartDateDay(currentDayOfMonth);


        for (Contract contract : contracts) {
            sendContract(convertContractToProducerDto(contract));
        }
    }

    @Override
    public void sendContract(ProducerDto producerDto) {
        try {
            String jsonMessage = convertProducerDtoToJson(producerDto);

            kafkaTemplate.send("contractDelivery", jsonMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String convertProducerDtoToJson(ProducerDto producerDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(producerDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private ProducerDto convertContractToProducerDto(Contract contract) {
        ProducerDto producerDto = new ProducerDto();
        producerDto.setUsername(contract.getUsername());
        producerDto.setCompany(contract.getCompanyName());
        producerDto.setEquipment(contract.getEquipment());
        producerDto.setStatus(EContractStatus.Finished);

        return producerDto;
    }
}
