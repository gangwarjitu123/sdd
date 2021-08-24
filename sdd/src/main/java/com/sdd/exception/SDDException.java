package com.sdd.exception;

import lombok.Getter;

@Getter
public class SDDException extends RuntimeException{
    private String field;
    private int status;

    public SDDException(String field,int status,String message){
        super(message);
        this.field =field;
        this.status = status;
    }

    public SDDException(int status,String message){
        super(message);
        this.status = status;
    }
}
