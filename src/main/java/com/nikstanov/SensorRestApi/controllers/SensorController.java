package com.nikstanov.SensorRestApi.controllers;

import com.nikstanov.SensorRestApi.dto.SensorDTO;
import com.nikstanov.SensorRestApi.models.Sensor;
import com.nikstanov.SensorRestApi.services.SensorService;
import com.nikstanov.SensorRestApi.utills.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final ModelMapper mapper;
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;

    public SensorController(ModelMapper mapper, SensorService sensorService, SensorValidator sensorValidator) {
        this.mapper = mapper;
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }

    @GetMapping()
    public List<SensorDTO> getAll(){
        return sensorService.getAll().stream().map(sensor -> mapper.map(sensor, SensorDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/some")
    public ResponseEntity<HttpStatus> test(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> addNew(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){
        sensorValidator.validate(sensorDTO, bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder str = new StringBuilder();
            for(FieldError error : bindingResult.getFieldErrors()){
                str.append(error.getField()).append(" - ").append(error.getDefaultMessage());
            }
            throw new SensorNotCreatedException(str.toString());
        }
        sensorService.save(mapper.map(sensorDTO, Sensor.class));
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerAlreadyExistException(AlreadyExistException e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerNotFoundException(NotFoundException e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerSensorNotCreatedException(SensorNotCreatedException e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }
}
