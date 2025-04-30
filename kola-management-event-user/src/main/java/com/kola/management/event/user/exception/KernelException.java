package com.notary.management.kernel.exception;

public class KernelException extends Throwable {

    public int code;
    public String message;

    public KernelException(){
        super("Notary System General Error ");
    }

    public KernelException(int code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }

    public KernelException(String message){
        super(message);
    }

    public KernelException(String message, Throwable throwable){
        super(message,throwable);
    }

//    public KernelExceptionException(String message, Class parentClass){
//        super(message,parentClass);
//    }
}
