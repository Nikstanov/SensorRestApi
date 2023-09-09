package com.nikstanov.SensorRestApi.utills;

import com.nikstanov.SensorRestApi.dto.SensorDTO;
import com.nikstanov.SensorRestApi.services.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(SensorDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;
        if(sensorService.existByName(sensorDTO.getName())){
            errors.rejectValue("name", "", "Sensor already exist");
            throw new AlreadyExistException("Sensor already exist");
        }
    }
}
