package com.e2.privatehospital.Controllers;

import com.e2.privatehospital.Dto.ContractDto;
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
    public void produce(@RequestBody ContractDto contract) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContract = objectMapper.writeValueAsString(contract);
        template.send("contract", jsonContract);
    }
}
