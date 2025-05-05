package com.kola.management.event.user.business.exceptions;

import com.kola.management.event.kernel.exception.KernelException;

public class UserBusinessException extends KernelException {
    public UserBusinessException(String message) {
        super(message);
    }
}
