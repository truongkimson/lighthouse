package com.nus.lighthouse.exception;

public class EmailAlreadyExistsException extends Exception{
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
