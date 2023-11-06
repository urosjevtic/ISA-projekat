package com.e2.medicalsystem.exception;

public class EmailAlreadyExistException extends RuntimeException{
    public EmailAlreadyExistException(String message)
    {
        super(message);
    }
}
