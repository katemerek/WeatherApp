package com.github.katemerek.WeatherApp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.katemerek.WeatherApp.mapper.SensorMapper;
import com.github.katemerek.WeatherApp.models.Sensor;
import com.github.katemerek.WeatherApp.services.SensorsService;
import com.github.katemerek.WeatherApp.util.SensorValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SensorController.class)
class SensorControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    private SensorsService sensorsService;
    @MockBean
    private SensorValidator sensorValidator;
    @MockBean
    private SensorMapper sensorMapper;


    private List<Sensor> getSensors() {
        Sensor one = new Sensor(1, "sensor_test1");
        Sensor two = new Sensor(2, "sensor_test2");
        return List.of(one, two);
    }

    @DisplayName("GET /sensors возвращает HTTP-ответ со статусом 200 ОК и список зарегистрированных сенсоров")
    @Test
    void getAllSensors_ReturnsAllSensors() throws Exception {
        Mockito.when(this.sensorsService.findAll()).thenReturn(getSensors());

        mvc.perform(get("/api/v1/sensors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @DisplayName("POST /sensors/registration возвращает HTTP-ответ со статусом 200 CREATED")
    @Test
    void createSensor_ReturnsCreated() throws Exception {
        Sensor sensorToAdd = new Sensor(1, "sensor_test1");
        Mockito.when(this.sensorsService.save(sensorToAdd)).thenReturn(sensorToAdd);

        mvc.perform(post("/api/v1/sensors/registration")
                        .content(mapper.writeValueAsString(sensorToAdd))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }
}