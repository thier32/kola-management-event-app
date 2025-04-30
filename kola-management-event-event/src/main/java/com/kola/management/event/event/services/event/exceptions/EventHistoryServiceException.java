package com.kola.management.event.event.services.event.exceptions;

import com.kola.management.event.kernel.exception.KernelException;

public class EventHistoryServiceException extends KernelException {
    public EventHistoryServiceException(String message) {
        super(message);
    }
}
