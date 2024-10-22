package com.github.katemerek.WeatherApp.mapper;

import com.github.katemerek.WeatherApp.dto.MeasurementDTO;
import com.github.katemerek.WeatherApp.models.Measurement;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = SensorMapper.class)
public interface MeasurementMapper {

    Measurement toMeasurement(MeasurementDTO measurementDTO);

    @Mapping(target = "sensor.id", ignore = true)
    MeasurementDTO toMeasurementDTO (Measurement measurement);

    List<MeasurementDTO> toMeasurementDTOList(List<Measurement> measurements);

}
