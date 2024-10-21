package com.github.katemerek.WeatherApp.controllers;

import com.github.katemerek.WeatherApp.dto.MeasurementDTO;
import com.github.katemerek.WeatherApp.mapper.MeasurementMapper;
import com.github.katemerek.WeatherApp.models.Measurement;
import com.github.katemerek.WeatherApp.services.MeasurementsService;
import com.github.katemerek.WeatherApp.util.ErrorsUtil;
import com.github.katemerek.WeatherApp.util.MeasurementValidator;
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
import io.swagger.v3.oas.annotations.*;

import java.util.List;

@Tag(name = "Measurement", description = "The Measurement API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/measurements")
public class MeasurementController {

    private final MeasurementsService measurementsService;
    private final MeasurementValidator measurementValidator;
    private final MeasurementMapper measurementMapper;

    @Operation(summary = "Gets all measurements")
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the measurements",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = MeasurementDTO.class)))
                    })
    @GetMapping
    public List <MeasurementDTO> getMeasurements() {
        List<Measurement> measurements = measurementsService.findAll();
        return measurementMapper.toMeasurementDTOList(measurements);
    }


    @Operation(summary = "Count the number of rainy days")
            @ApiResponse(
                    responseCode = "200",
                    description = "Number of rainy days")
    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount() {
        return measurementsService.findAll().stream().filter(Measurement::getRaining).count();
    }


    @Operation(summary = "Add a new measurement")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "CREATED"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Measurement not added, sensor with such name is not registered in the system")})
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> createMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {

        Measurement measurementAdd = measurementMapper.toMeasurement(measurementDTO);
        measurementValidator.validate(measurementAdd, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnError(bindingResult);
        }
        measurementsService.save(measurementAdd);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}

