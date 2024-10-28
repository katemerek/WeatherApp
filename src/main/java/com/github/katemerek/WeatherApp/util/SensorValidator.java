package com.github.katemerek.WeatherApp.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.github.katemerek.WeatherApp.models.Sensor;
import com.github.katemerek.WeatherApp.services.SensorsService;

@Component
public class SensorValidator implements Validator {

    private final SensorsService sensorsService;
    public SensorValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        if (sensorsService.findByName(sensor.getName()).isPresent())
            errors.rejectValue("name", String.valueOf(HttpStatus.BAD_REQUEST), "This sensor is already created");
    }
}

