package com.wawa.oms.exception;


public class RestException extends MicroServiceException {

    private static final long serialVersionUID = 1L;

    public RestException(String errorCode, String message) {
        super(errorCode, message);
    }
}
