package com.github.katemerek.WeatherApp.mapper;

import com.github.katemerek.WeatherApp.dto.SensorDTO;
import com.github.katemerek.WeatherApp.models.Sensor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SensorMapper {
    Sensor toSensor(SensorDTO sensorDTO);

    SensorDTO toSensorDTO(Sensor sensor);

    List<SensorDTO> toSensorDTOList (List<Sensor> sensors);
}