package com.github.katemerek.WeatherApp.controllers;

import com.github.katemerek.WeatherApp.util.ErrorNotCreated;
import com.github.katemerek.WeatherApp.util.SensorValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.github.katemerek.WeatherApp.dto.SensorDTO;
import com.github.katemerek.WeatherApp.models.Sensor;
import com.github.katemerek.WeatherApp.services.SensorsService;
import com.github.katemerek.WeatherApp.util.ErrorResponse;
import com.github.katemerek.WeatherApp.util.ErrorsUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorsService sensorsService;
    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;

    public SensorController(SensorsService sensorsService, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.sensorsService = sensorsService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<SensorDTO> getAllSensors() {
        return sensorsService.findAll().stream().map(this::convertToSensorDTO).collect(Collectors.toList());
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> createSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {

        Sensor sensorToAdd = convertToSensor(sensorDTO);
        sensorValidator.validate(sensorToAdd, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnError(bindingResult);
        }
        sensorsService.save(sensorToAdd);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }


    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    private SensorDTO convertToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleSensorNotCreatedException(ErrorNotCreated e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}

