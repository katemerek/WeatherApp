package ru.merkulova.WeatherApp.dto;

import lombok.Data;
import ru.merkulova.WeatherApp.models.Measurement;

import java.util.List;
@Data
public class MeasurementsResponse {
    private List<MeasurementDTO> measurements;

    public MeasurementsResponse(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}
