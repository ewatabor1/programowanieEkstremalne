package com.example.demo.fridgemanager.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PositiveValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Positive {
    String message() default "constraints.positive";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

