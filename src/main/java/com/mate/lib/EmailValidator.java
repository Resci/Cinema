package com.mate.lib;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    public static final String EMAIL_VALIDATION_REGEX = "^(.+)@(.+)$";

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String field, ConstraintValidatorContext context) {
        if (field != null) {
            return field.matches(EMAIL_VALIDATION_REGEX);
        }
        return false;
    }
}
