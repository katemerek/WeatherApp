package ru.merkulova.WeatherApp.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorDTO {
    @Column(name="name")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters")
    @NotEmpty(message ="Name should not be empty")
    private String name;
}

