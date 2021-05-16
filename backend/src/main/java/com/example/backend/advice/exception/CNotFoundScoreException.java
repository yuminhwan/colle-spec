package com.example.backend.advice.exception;

public class CNotFoundScoreException extends RuntimeException{

    public CNotFoundScoreException(String msg, Throwable t) {
        super(msg, t);
    }

    public CNotFoundScoreException(String msg) {
        super(msg);
    }

    public CNotFoundScoreException() {
        super();
    }

}
