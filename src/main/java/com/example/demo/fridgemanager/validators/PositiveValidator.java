package com.example.demo.fridgemanager.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class PositiveValidator implements ConstraintValidator<Positive, BigDecimal> {
    @Override
    public void initialize(Positive constraintAnnotation) {

    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        return value != null && value.compareTo(BigDecimal.ZERO) > 0;
    }
}
