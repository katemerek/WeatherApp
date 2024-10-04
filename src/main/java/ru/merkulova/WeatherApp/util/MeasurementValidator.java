package ru.merkulova.WeatherApp.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.merkulova.WeatherApp.models.Measurement;
import ru.merkulova.WeatherApp.services.MeasurementsService;
import ru.merkulova.WeatherApp.services.SensorsService;

@Component
public class MeasurementValidator implements Validator {

    private final SensorsService sensorsService;

    public MeasurementValidator(MeasurementsService measurementsService, SensorsService sensorsService) {
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
        if (sensorsService.findByName(measurement.getSensor_owner().getName()).isEmpty()) //тут проблема
        errors.rejectValue("name", "This sensor don't exist!");

    }
}
