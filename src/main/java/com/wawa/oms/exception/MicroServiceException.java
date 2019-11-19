package com.wawa.oms.exception;

import lombok.Data;

import java.util.List;

@Data
public class MicroServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    private String errorCode = null;
    private List<Error> errors;

    public MicroServiceException() {
        super();
    }

    public MicroServiceException(String message, Exception e) {
        super(message, e);
    }

    public MicroServiceException(String message) {
        super(message);
    }

    public MicroServiceException(Throwable e) {
        super(e);
    }

    public MicroServiceException(String errorCode, String message, List<Error> errors) {
        super(message);
        this.errorCode = errorCode;
        this.errors = errors;
    }

    public MicroServiceException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public MicroServiceException(String errorCode, String message, Exception e) {
        super(message, e);
        this.errorCode = errorCode;
    }

}
