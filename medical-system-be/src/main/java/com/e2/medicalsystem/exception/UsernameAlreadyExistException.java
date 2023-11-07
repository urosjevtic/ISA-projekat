package com.e2.medicalsystem.exception;

public class UsernameAlreadyExistException extends RuntimeException{
    public UsernameAlreadyExistException(String message)
    {
        super(message);
    }
}
