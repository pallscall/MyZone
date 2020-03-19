package com.example.demo.Exception;

public class ErrorException extends RuntimeException {
    private String message;
    private int code;

    public ErrorException(IErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public ErrorException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
