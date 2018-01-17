package com.codecool.freefoodmeetup.exceptions;

public class WrongValueException extends BadRequestException {
    public WrongValueException(String message) {
        super(message);
    }

}
