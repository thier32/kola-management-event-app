package com.kola.management.event.event.services.event.exceptions;

import com.kola.management.event.kernel.exception.KernelException;

public class EventServiceException extends KernelException {
    public EventServiceException(String message) {
        super(message);
    }
}
