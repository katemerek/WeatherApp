package ru.merkulova.WeatherApp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.merkulova.WeatherApp.dto.MeasurementDTO;
import ru.merkulova.WeatherApp.dto.MeasurementsResponse;
import ru.merkulova.WeatherApp.models.Measurement;
import ru.merkulova.WeatherApp.services.MeasurementsService;
import ru.merkulova.WeatherApp.util.ErrorNotCreated;
import ru.merkulova.WeatherApp.util.ErrorResponse;
import ru.merkulova.WeatherApp.util.ErrorsUtil;
import ru.merkulova.WeatherApp.util.MeasurementValidator;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementsService measurementsService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;


    public MeasurementController(MeasurementsService measurementsService, ModelMapper modelMapper, MeasurementValidator measurementValidator) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }

    @GetMapping
    public MeasurementsResponse getMeasurements() {
        return new MeasurementsResponse(measurementsService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList()));
    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount() {
        return measurementsService.findAll().stream().filter(Measurement::getRaining).count();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> createMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {

        Measurement measurementAdd = modelMapper.map(measurementDTO, Measurement.class);
        measurementValidator.validate(measurementAdd, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnError(bindingResult);
        }
        measurementsService.save(measurementAdd);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
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

