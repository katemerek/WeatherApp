package com.github.katemerek.WeatherApp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.katemerek.WeatherApp.models.Sensor;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorsRepository extends JpaRepository<Sensor, Integer> {
    List<Sensor> findAll();
    Optional<Sensor> findByName(String name);
}
