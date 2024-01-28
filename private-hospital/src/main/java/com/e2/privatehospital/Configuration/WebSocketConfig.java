package com.e2.privatehospital.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/socket") // Definisemo endpoint koji ce klijenti koristiti da se povezu sa serverom.
                // U ovom slucaju, URL za konekciju ce biti http://localhost:8080/socket/
                .setAllowedOrigins("*") // Dozvoljavamo serveru da prima zahteve bilo kog porekla
                .withSockJS(); // Koristi se SockJS: https://github.com/sockjs/sockjs-protocol
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/socket-subscriber")
                .enableSimpleBroker("/socket-publisher");

    }
}
