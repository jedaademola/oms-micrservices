package com.wawa.oms.exception;

public class BaseException extends Exception {
    private static final long serialVersionUID = 1L;

    public BaseException() {
        super();
    }

    public BaseException(String message, Exception e) {
        super(message, e);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable e) {
        super(e);
    }
}
