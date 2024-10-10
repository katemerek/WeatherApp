package com.github.katemerek.WeatherApp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.katemerek.WeatherApp.models.Measurement;
import com.github.katemerek.WeatherApp.repositories.MeasurementsRepository;


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

    public List<Measurement> findAll() {
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

