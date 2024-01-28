package com.e2.medicalsystem.service.impl;

import com.e2.medicalsystem.service.EmailSenderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;

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

    public void sendEmailWithQrCode(String toMail, String subject, String body, byte[] qrCodeBytes) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            // Use true flag to indicate multipart message with attachments
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("uros.jevtic8888@gmail.com");
            helper.setTo(toMail);
            helper.setSubject(subject);
            helper.setText(body);

            // Add attachment
            helper.addAttachment("qrcode.png", new ByteArrayResource(qrCodeBytes));

            // Send the email
            mailSender.send(message);

            System.out.println("Message sent successfully...");
        } catch (MessagingException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
}
