package com.example.inventoryinstrument.service.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ExceptionService {

    public <T> void methodValidationInstrumentException(T arg) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(arg);
        violations.forEach(violation -> {
            System.out.println(violation.getMessage());
        });
    }

}
