package com.e2.medicalsystem.kafka;

import com.e2.medicalsystem.dto.ContractDto;
import com.e2.medicalsystem.dto.KafkaCoords;
import com.e2.medicalsystem.dto.LatLng;
import com.e2.medicalsystem.model.Contract;
import com.e2.medicalsystem.service.ContractService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CoordinateConsumer {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private ContractService contractService;
    @KafkaListener(id="simulatorListener",topics = "simulator", groupId = "simulator",
            containerFactory = "kafkaListenerContainerFactory",autoStartup = "false")

    public void listenSimulator(String message)
    {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            KafkaCoords coords = objectMapper.readValue(message, KafkaCoords.class);
            LatLng latLng = new LatLng(coords.getLat(),coords.getLng());
            simpMessagingTemplate.convertAndSendToUser(coords.getUser(),"/queue/simulator",latLng);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @KafkaListener(id="contractListener",topics = "contract", groupId = "contract",
            containerFactory = "kafkaListenerContainerFactory",autoStartup = "true")

    public void listenContract(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ContractDto contractDto = objectMapper.readValue(message, ContractDto.class);
            saveOrUpdateContractInDatabase(contractDto);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveOrUpdateContractInDatabase(ContractDto contractDto) {
        Contract existingContract = contractService.findByUsernameAndCompanyName(
                contractDto.getUsername(),  contractDto.getCompanyName());

        if (existingContract != null) {
            updateExistingContract(existingContract, contractDto);
        } else {
            createNewContract(contractDto);
        }
    }

    private void updateExistingContract(Contract existingContract, ContractDto contractDto) {
        existingContract.setStartDate(contractDto.getStartDate());
        existingContract.setUsername(contractDto.getUsername());
        existingContract.setEquipment(contractDto.getEquipment());
        existingContract.setCompanyName(contractDto.getCompanyName());

        contractService.updateContract(existingContract);
    }

    private void createNewContract(ContractDto contractDto) {
        Contract newContract = convertDtoToEntity(contractDto);
        contractService.saveContract(newContract);
    }

    private Contract convertDtoToEntity(ContractDto contractDto) {
        Contract contract = new Contract();
        contract.setUsername(contractDto.getUsername());
        contract.setStartDate(contractDto.getStartDate());
        contract.setCompanyName(contractDto.getCompanyName());
        contract.setEquipment(contractDto.getEquipment());
        return contract;
    }
}
