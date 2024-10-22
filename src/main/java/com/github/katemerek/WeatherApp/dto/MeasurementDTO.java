package com.github.katemerek.WeatherApp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.github.katemerek.WeatherApp.models.Sensor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "measurement")
public class MeasurementDTO {

    @Min(-100)
    @Max(100)
    @NotNull
    private Double value;

    private Boolean raining;

    @ManyToOne
    @JoinColumn(name="sensor", referencedColumnName = "name")
    @NotNull
    private Sensor sensor;
}
