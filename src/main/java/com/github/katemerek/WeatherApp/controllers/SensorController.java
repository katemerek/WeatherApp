package com.github.katemerek.WeatherApp.controllers;

import com.github.katemerek.WeatherApp.mapper.SensorMapper;
import com.github.katemerek.WeatherApp.util.SensorValidator;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.github.katemerek.WeatherApp.dto.SensorDTO;
import com.github.katemerek.WeatherApp.models.Sensor;
import com.github.katemerek.WeatherApp.services.SensorsService;
import com.github.katemerek.WeatherApp.util.ErrorsUtil;
import io.swagger.v3.oas.annotations.*;

import java.util.List;

@Tag(name = "Sensor", description = "The Sensor API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sensors")
public class SensorController {
    private final SensorsService sensorsService;
    private final SensorValidator sensorValidator;
    private final SensorMapper sensorMapper;


    @Operation(summary = "Gets all sensors")
            @ApiResponse (
                    responseCode = "200",
                    description = "Found the sensors",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = SensorDTO.class)))
                    })
    @GetMapping
    public List<SensorDTO> getAllSensors() {
        List <Sensor> sensors = sensorsService.findAll();
        return sensorMapper.toSensorDTOList(sensors);
    }


    @Operation(summary = "Add a new sensor")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "CREATED"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Sensor not registered, this name is already exists")})
    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> createSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {

        Sensor sensorToAdd = sensorMapper.toSensor(sensorDTO);
        sensorValidator.validate(sensorToAdd, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnError(bindingResult);
        }
        sensorsService.save(sensorToAdd);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}

