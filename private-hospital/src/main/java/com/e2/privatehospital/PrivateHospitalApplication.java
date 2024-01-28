package com.e2.privatehospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling

@SpringBootApplication
public class PrivateHospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrivateHospitalApplication.class, args);
    }

}
