package com.e2.medicalsystem.controller;

import com.e2.medicalsystem.dto.ProducerDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/producer")
public class ProducerController {
    private KafkaTemplate<String, String> template;
    public ProducerController(KafkaTemplate<String, String> template){
        this.template = template;
    }

    @PostMapping("/send")
    public void produce(@RequestBody ProducerDto producerDto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContract = objectMapper.writeValueAsString(producerDto);
        template.send("contractDelivery", jsonContract);
    }
}
