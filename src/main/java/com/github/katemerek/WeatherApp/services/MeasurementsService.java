package com.github.katemerek.WeatherApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.katemerek.WeatherApp.models.Measurement;
import com.github.katemerek.WeatherApp.repositories.MeasurementsRepository;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;
    private final SensorsService sensorsService;

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

