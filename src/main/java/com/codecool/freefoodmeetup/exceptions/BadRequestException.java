package com.codecool.freefoodmeetup.exceptions;

public abstract class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
