package com.e2.medicalsystem.service;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface QrCodeService {
    byte[] generateQrCode(String content, int width, int height) throws WriterException, IOException;
}
