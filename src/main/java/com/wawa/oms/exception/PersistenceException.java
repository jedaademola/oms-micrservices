package com.wawa.oms.exception;


public class PersistenceException extends RuntimeException {

    private static final long serialVersionUID = 1409075783578543469L;

    public PersistenceException() {
        super();
    }

    public PersistenceException(String message, Exception e) {
        super(message, e);
    }

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(Throwable e) {
        super(e);
    }

}
