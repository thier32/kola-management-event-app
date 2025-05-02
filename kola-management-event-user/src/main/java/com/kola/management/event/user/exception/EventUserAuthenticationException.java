package com.kola.management.event.user.exception;


import org.springframework.security.core.AuthenticationException;

public class EventUserAuthenticationException extends AuthenticationException {
    public EventUserAuthenticationException(String message){
        super(message);
    }
}
