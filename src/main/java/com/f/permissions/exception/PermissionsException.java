package com.f.permissions.exception;

/**
 * @Description:
 * @author: feng
 * @date: 2020-06-07
 */
public class PermissionsException extends RuntimeException{
    private String message;

    public PermissionsException(String message){
        super(message);
        this.message = message;
    }

    public PermissionsException(Exception e){
        super(e);
    }
}
