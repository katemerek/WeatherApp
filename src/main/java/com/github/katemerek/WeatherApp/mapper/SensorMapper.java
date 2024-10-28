package com.github.katemerek.WeatherApp.mapper;

import com.github.katemerek.WeatherApp.dto.SensorDTO;
import com.github.katemerek.WeatherApp.models.Sensor;
import com.github.katemerek.WeatherApp.repositories.SensorsRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SensorMapper {

    Sensor toSensor(SensorDTO sensorDTO);

    SensorDTO toSensorDTO(Sensor sensor);
}