package com.wawa.oms.exception;

import java.util.List;

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


    /**
     * Returns the errorCode
     *
     * @return errorCode String
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the errorCode
     *
     * @param errorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errors
     */
    public List<Error> getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

}
