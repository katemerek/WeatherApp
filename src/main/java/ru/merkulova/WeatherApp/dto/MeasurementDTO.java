package ru.merkulova.WeatherApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.merkulova.WeatherApp.models.Sensor;

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

    @NotNull
    @ManyToOne
    @JoinColumn(name="sensor", referencedColumnName = "name")
    private Sensor sensor;

}
