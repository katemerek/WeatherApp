package com.github.katemerek.WeatherApp.util;

import com.github.katemerek.WeatherApp.models.Measurement;
import com.github.katemerek.WeatherApp.services.SensorsService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {

    private final SensorsService sensorsService;

    public MeasurementValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;
        if (measurement.getSensor()==null) {return;}
        if(sensorsService.findByName(measurement.getSensor().getName()).isEmpty()) {
        errors.rejectValue("name", "This sensor don't exist!");}
    }
}
