package com.github.katemerek.WeatherApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.katemerek.WeatherApp.models.Sensor;
import com.github.katemerek.WeatherApp.repositories.SensorsRepository;


import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SensorsService {

    private final SensorsRepository sensorsRepository;

    public List<Sensor> findAll() {return sensorsRepository.findAll();}

    public Optional<Sensor> findByName(String name) {
        return sensorsRepository.findByName(name);
    }

    @Transactional
    public Sensor save(Sensor sensor) {
        return sensorsRepository.save(sensor);
    }

}

