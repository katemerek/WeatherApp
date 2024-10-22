package com.github.katemerek.WeatherApp.controllers;

import com.github.katemerek.WeatherApp.util.ErrorNotCreated;
import com.github.katemerek.WeatherApp.util.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorsController {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleSensorNotCreatedException(ErrorNotCreated e) {
        return new ErrorResponse(e.getMessage(), System.currentTimeMillis()) {};
    }
}
