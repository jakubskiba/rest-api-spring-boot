package com.codecool.freefoodmeetup.exceptions;

public class ExceptionModel {
    private String exceptionType;
    private String message;

    public ExceptionModel(String message, String exceptionType) {
        this.message = message;
        this.exceptionType = exceptionType;
    }

    public String getMessage() {
        return message;
    }

    public String getExceptionType() {
        return exceptionType;
    }
}
