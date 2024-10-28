package com.github.katemerek.WeatherApp.mapper;

import com.github.katemerek.WeatherApp.dto.MeasurementDTO;
import com.github.katemerek.WeatherApp.models.Measurement;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = SensorMapper.class)
public interface MeasurementMapper {

    Measurement toMeasurement(MeasurementDTO measurementDTO);

    @Mapping(target = "sensor.id", ignore = true)
    MeasurementDTO toMeasurementDTO(Measurement measurement);

}
