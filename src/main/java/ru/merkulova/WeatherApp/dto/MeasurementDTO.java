package ru.merkulova.WeatherApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDTO {

    @Column(name="value")
    @Min(-100)
    @Max(100)
    @NotNull
    private Double value;

    @Column(name="raining")
    private Boolean raining;

    @NotEmpty(message = "Sensor should not be empty")
    @Column(name="sensor")
    private String sensor;

    @JsonProperty("sensor") //возможно надо убрать. Не могу прочитать вложенный джейсон
    private JsonNode sensorData;

}
