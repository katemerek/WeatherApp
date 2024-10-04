package ru.merkulova.WeatherApp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="Measurement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Measurement implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="value")
    @Min(-100)
    @Max(100)
    @NotNull
    private Double value;


    @Column(name="raining")
    private Boolean raining;

    @NotEmpty(message = "Sensor should not be empty")
    @Column(name="sensor",insertable=false, updatable=false)
    private String sensor;

    @Column(name="time_measurement")
    private LocalDateTime timeMeasurement;

    @JsonProperty("sensor")
    private String sensorData;

    @ManyToOne
    @JoinColumn(name="sensor", referencedColumnName = "name")
    private Sensor sensor_owner;
}

