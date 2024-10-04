package ru.merkulova.WeatherApp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.merkulova.WeatherApp.models.Measurement;
import ru.merkulova.WeatherApp.repositories.MeasurementsRepository;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;
    private final SensorsService sensorsService;

    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsService sensorsService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsService = sensorsService;
    }

    public List<Measurement> findAll(){
        return measurementsRepository.findAll();
    }
    @Transactional
    public Measurement save(Measurement measurement) {
        enrichMeasurement(measurement);
        return measurementsRepository.save(measurement);
    }

    public void enrichMeasurement(Measurement measurement) {

        measurement.setSensor(sensorsService.findByName(measurement.getSensor().getName()).get());
        measurement.setTimeMeasurement(LocalDateTime.now());
    }

}

