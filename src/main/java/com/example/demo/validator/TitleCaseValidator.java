package com.example.demo.validator;

import com.example.demo.annotations.TitleCase;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TitleCaseValidator implements ConstraintValidator <TitleCase, String> {

    @Override
    public void initialize(TitleCase constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        // add validate code
        if (value.equals("NOT_CORRECT")) {
            return false;
        }

        return true;
    }
}
