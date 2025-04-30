package com.kola.management.event.kernel.exception;

public class EventKernelException extends Throwable {

    public int code;
    public String message;

    /**
     *
     */
    public EventKernelException(){
        super("Event System General Error ");
    }

    /**
     *
     * @param code
     * @param message
     */
    public EventKernelException(int code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     *
     * @param message
     */
    public EventKernelException(String message){
        super(message);
    }

    /**
     *
     * @param message
     * @param throwable
     */
    public EventKernelException(String message, Throwable throwable){
        super(message,throwable);
    }

}
