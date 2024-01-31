package com.e2.medicalsystem.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

public class KafkaProducerConfig {
    private static final String KAFKA_BROKER = "localhost:9092";

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configurations= new HashMap<>();
        /*
         * BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER - Lista parova host:portova koja se koristi za uspostavu
         * 							konekcije sa Kafka klasterom

         * KEY_SERIALIZER_CLASS_CONFIG - Klasa za serijalizaciju ključeva
         * VALUE_SERIALIZER_CLASS_CONFIG - Klasa za serijalizaciju vrednosti
         * 							KLJUC: VREDNOST se čuva na Kafkinom serveru i slanje se mora serijalizovati
         * */
        configurations.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
        configurations.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configurations.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configurations);
    }
}
