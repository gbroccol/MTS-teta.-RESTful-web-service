package com.example.demo.annotations;

import com.example.demo.validator.AuthorAllowedValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = AuthorAllowedValidator.class)
public @interface AuthorAllowed {

    String[] authors();

    String message() default "Автор недопустим";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
