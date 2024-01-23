package com.e2.medicalsystem.kafka;

import com.e2.medicalsystem.dto.LatLng;
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
    @KafkaListener(id="simulatorListener",topics = "simulator", groupId = "simulator",
            containerFactory = "kafkaListenerContainerFactory",autoStartup = "false")
    public void listenSimulator(String message)
    {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            LatLng latLng = objectMapper.readValue(message,LatLng.class);
            simpMessagingTemplate.convertAndSendToUser("user1","/queue/simulator",latLng);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
