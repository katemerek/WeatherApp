package com.github.katemerek.WeatherApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementsResponse {
    private List<MeasurementDTO> measurements;
}
