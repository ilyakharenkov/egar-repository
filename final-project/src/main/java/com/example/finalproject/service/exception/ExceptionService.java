package com.example.finalproject.service.exception;

import jakarta.validation.*;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ExceptionService{

    //Валидация сущностей.
    public <T> void methodValidationInstrumentException(T arg){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(arg);
        violations.forEach(violation -> {
            System.out.println(violation.getMessage());
        });
    }

}
