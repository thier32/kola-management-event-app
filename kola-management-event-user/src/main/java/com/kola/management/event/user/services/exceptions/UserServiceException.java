package com.kola.management.event.user.services.exceptions;


import com.kola.management.event.kernel.exception.KernelException;

public class UserServiceException extends KernelException {
    public UserServiceException(String message) {
        super(message);
    }
}
