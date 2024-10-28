package com.github.katemerek.WeatherApp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.katemerek.WeatherApp.mapper.MeasurementMapper;
import com.github.katemerek.WeatherApp.models.Measurement;
import com.github.katemerek.WeatherApp.models.Sensor;
import com.github.katemerek.WeatherApp.services.MeasurementsService;
import com.github.katemerek.WeatherApp.util.MeasurementValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest (MeasurementController.class)
class MeasurementControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private MeasurementsService measurementsService;
    @MockBean
    private MeasurementValidator measurementValidator;
    @MockBean
    private MeasurementMapper measurementMapper;

    private List<Measurement> getMeasurements() {
        Measurement one = new Measurement(2, 23.4, true, LocalDateTime.now(), new Sensor(1, "sensor_alfa"));
        Measurement two = new Measurement(3, 11.5, true, LocalDateTime.now(), new Sensor(2, "sensor_beta"));
        return List.of(one, two);
    }

    @DisplayName("GET /measurements возвращает HTTP-ответ со статусом 200 ОК и список всех измерений")
    @Test
    void getMeasurements_ReturnAllMeasurements() throws Exception {
        Mockito.when(this.measurementsService.findAll()).thenReturn(getMeasurements());

        mvc.perform(get("/api/v1/measurements"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @DisplayName("GET /measurements/rainyDaysCount возвращает количество дождливых дней")
    @Test
    void getRainyDaysCount() throws Exception {
        Mockito.when(this.measurementsService.findAll()).thenReturn(getMeasurements());
        mvc.perform(get("/api/v1/measurements/rainyDaysCount"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("POST /measurements/add возвращает HTTP-ответ со статусом 200 CREATED")
    @Test
    void createMeasurement_ReturnsCreated() throws Exception {
        Measurement one = new Measurement(2, 23.4, true, LocalDateTime.now(), new Sensor(1, "sensor_alfa"));
        Mockito.when(this.measurementsService.save(one)).thenReturn(one);
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/measurements/add")
                .content(mapper.writeValueAsString(one))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }
}