package com.e2.privatehospital.Kafka;

import com.e2.privatehospital.Dto.ContractDeliveryStatusDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ContractDeliveryConsumer {
    @KafkaListener(id="contractListener",topics = "contractDelivery", groupId = "contractDelivery",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenContract(String message){
        try {
            System.out.println(message);
            ObjectMapper objectMapper = new ObjectMapper();
            ContractDeliveryStatusDto deliveryStatus = objectMapper.readValue(message,ContractDeliveryStatusDto.class);
            System.out.println(deliveryStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
