package com.umistore.imsys.exception;

public class CustomDuplicateEmailException extends RuntimeException {
    public CustomDuplicateEmailException(String message) {
        super(message);
    }
}
