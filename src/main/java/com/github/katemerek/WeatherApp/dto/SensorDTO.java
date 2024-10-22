package com.github.katemerek.WeatherApp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Sensor")
public class SensorDTO {


    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters")
    @NotBlank(message ="Name should not be empty")
    private String name;
}

