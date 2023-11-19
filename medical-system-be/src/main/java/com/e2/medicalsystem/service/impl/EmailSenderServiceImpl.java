package com.e2.medicalsystem.service.impl;

import com.e2.medicalsystem.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toMail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("uros.jevtic8888@gmail.com");
        message.setTo(toMail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);

        System.out.println("Message sent successfully...");
    }
}
