package com.wawa.oms.exception;

public class NotFoundException extends MicroServiceException {
    private static final long serialVersionUID = 1L;

    public NotFoundException(String errorCode, String message) {
        super(errorCode, message);
    }
}
