package com.e2.medicalsystem.service;

public interface EmailSenderService {
    public void sendEmail(String toMail, String subject, String body);

    public void sendEmailWithQrCode(String toMail, String subject, String body, byte[] qrCodeBytes);
}
