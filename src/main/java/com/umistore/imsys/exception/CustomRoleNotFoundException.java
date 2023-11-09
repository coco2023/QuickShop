package com.umistore.imsys.exception;

public class CustomRoleNotFoundException extends RuntimeException {
    public CustomRoleNotFoundException(String message) {
        super(message);
    }

    // You can also add a constructor to pass the cause of the exception
    public CustomRoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // If you want to add extra fields or methods, you can do so here.
}
