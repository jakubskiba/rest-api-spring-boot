package com.codecool.freefoodmeetup.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public <T extends RuntimeException> ExceptionModel handleException(T e) {
        return new ExceptionModel(e.getMessage(), e.getClass().getSimpleName());
    }
}
