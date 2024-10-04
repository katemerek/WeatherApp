package ru.merkulova.WeatherApp.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.merkulova.WeatherApp.models.Sensor;
import ru.merkulova.WeatherApp.services.SensorsService;

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
            errors.rejectValue("name", "This sensor already exist!");

    }
}

