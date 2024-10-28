package com.github.katemerek.WeatherApp.mapper;

import com.github.katemerek.WeatherApp.dto.SensorDTO;
import com.github.katemerek.WeatherApp.models.Sensor;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
class SensorMapperTest {
    @Autowired
    private SensorMapper sensorMapper;

    @Test
    void testToSensor() {
        SensorDTO sensorDto = new SensorDTO("sensor test");
        Sensor sensor = sensorMapper.toSensor(sensorDto);
        assertNotNull(sensor);
        assertEquals("sensor test", sensor.getName());
    }

    @Test
    void testToSensorDTO() {
        Sensor sensor = new Sensor(1, "sensor test");
        SensorDTO sensorDTO = sensorMapper.toSensorDTO(sensor);
        assertNotNull(sensorDTO);
        assertEquals("sensor test", sensorDTO.getName());

    }
    @Test
    void testToSensorReturnNull () {
        SensorDTO sensorDto = new SensorDTO();
        Sensor sensor = sensorMapper.toSensor(sensorDto);
        assertNull(sensor.getName());
    }
    @Test
    void testToSensorDTOReturnNull () {
        Sensor sensor = new Sensor();
        SensorDTO sensorDto = sensorMapper.toSensorDTO(sensor);
        assertNull(sensorDto.getName());
    }
}