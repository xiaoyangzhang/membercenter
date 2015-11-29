package com.yimayhd.membercenter.service;

/**
 * Created by root on 15-11-29.
 */
public class BussinessException extends RuntimeException {

    private int errorCode = 0;

    public BussinessException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BussinessException(String message) {
        super(message);
    }

    public BussinessException() {
        super();
    }
}
