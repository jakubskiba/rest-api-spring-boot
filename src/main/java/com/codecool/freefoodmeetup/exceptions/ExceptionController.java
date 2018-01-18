package com.codecool.freefoodmeetup.exceptions;

import com.codecool.freefoodmeetup.FreeFoodMeetupApplication;
import com.codecool.freefoodmeetup.logger.LoggerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
@RestController
public class ExceptionController {

    LoggerService loggerService;

    public ExceptionController(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionModel handleAnyException(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        this.loggerService.logError(e.getMessage() + sw.toString());
        return new ExceptionModel("Server error occured. For details please look to logger.", "Internal server error");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public <T extends BadRequestException> ExceptionModel handleRuntimeException(T e) {
        return new ExceptionModel(e.getMessage(), e.getClass().getSimpleName());
    }
}
