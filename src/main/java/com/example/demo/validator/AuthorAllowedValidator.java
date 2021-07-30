package com.example.demo.validator;

import com.example.demo.annotations.AuthorAllowed;
//import org.apache.el.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.stream.Collectors;
//import java.util.stream.Collections;
import java.util.stream.Stream;
import java.util.Set;

//import static org.apache.el.stream.Stream.*;

public class AuthorAllowedValidator implements ConstraintValidator<AuthorAllowed, String> {

    private Set<String> authors;

//  вызывается 1 раз
    @Override
    public void initialize(AuthorAllowed constraintAnnotation) {
        authors = Stream.of(constraintAnnotation.authors()).collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return authors.contains(value);
//        return true;
    }
}
