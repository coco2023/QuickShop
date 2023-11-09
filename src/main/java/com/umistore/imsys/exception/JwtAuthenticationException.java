package com.umistore.imsys.exception;

public class JwtAuthenticationException extends RuntimeException {
    public JwtAuthenticationException(String e) {
        super(e);
    }
}
