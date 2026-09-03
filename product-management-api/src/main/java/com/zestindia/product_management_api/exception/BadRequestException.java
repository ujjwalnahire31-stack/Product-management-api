package com.zestindia.product_management_api.exception;

public class BadRequestException extends Exception {


    public BadRequestException(int value, String message){
        super(message);
    }

}
