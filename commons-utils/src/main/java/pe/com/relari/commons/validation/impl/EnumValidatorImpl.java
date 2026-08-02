package pe.com.relari.commons.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pe.com.relari.commons.validation.EnumValidator;

import java.util.List;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {
    private List<String> acceptedValues;

    @Override
    public void initialize(EnumValidator annotation) {
//        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
//                .map(Enum::name)
//                .toList();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
//        if (value == null) return true;
//        return acceptedValues.contains(value.toUpperCase());
        return true;
    }
}