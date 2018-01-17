package com.codecool.freefoodmeetup.exceptions;


public class ResourceNotFoundException extends BadRequestException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
