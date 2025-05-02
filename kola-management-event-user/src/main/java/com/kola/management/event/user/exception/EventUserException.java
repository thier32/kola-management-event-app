package com.kola.management.event.user.exception;

import com.kola.management.event.kernel.exception.KernelException;

public class EventUserException extends KernelException {

    public int code;
    public String message;

    public EventUserException(){
        super("Event System General Error ");
    }

    public EventUserException(int code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }

    public EventUserException(String message){
        super(message);
    }

    public EventUserException(String message, Throwable throwable){
        super(message,throwable);
    }

}
