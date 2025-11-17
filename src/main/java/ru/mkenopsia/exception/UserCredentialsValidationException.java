package ru.mkenopsia.exception;

import org.springframework.security.core.AuthenticationException;

public class UserCredentialsValidationException extends AuthenticationException {
    public UserCredentialsValidationException(String message) {
        super(message);
    }
}
