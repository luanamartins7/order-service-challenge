package com.acme.orders.service;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
