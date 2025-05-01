package com.kola.management.event.event.business.exceptions;

import com.kola.management.event.kernel.exception.KernelException;

public class EventBusinessException extends KernelException {
    public EventBusinessException(String message) {
        super(message);
    }
}
