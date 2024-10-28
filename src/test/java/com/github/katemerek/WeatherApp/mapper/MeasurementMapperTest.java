package com.github.katemerek.WeatherApp.mapper;

import com.github.katemerek.WeatherApp.dto.MeasurementDTO;
import com.github.katemerek.WeatherApp.models.Measurement;
import com.github.katemerek.WeatherApp.models.Sensor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RequiredArgsConstructor
class MeasurementMapperTest {
    @Autowired
    private MeasurementMapper measurementMapper;

    @Test
    void testToMeasurement() {
        MeasurementDTO measurementDTO = new MeasurementDTO(11.0, true, new Sensor(1, "sensor_alfa"));
        Measurement measurement = measurementMapper.toMeasurement(measurementDTO);
        assertNotNull(measurement);
        assertEquals(11.0, measurementDTO.getValue());
    }

    @Test
    void testToMeasurementDTO() {
        Measurement measurement = new Measurement(1, 11.0, true, LocalDateTime.now(), new Sensor(1, "sensor_alfa"));
        MeasurementDTO measurementDTO = measurementMapper.toMeasurementDTO(measurement);
        assertNotNull(measurementDTO);
        assertEquals(11.0, measurementDTO.getValue());
        assertEquals(measurement.getSensor().getName(), measurementDTO.getSensor().getName());
    }
}