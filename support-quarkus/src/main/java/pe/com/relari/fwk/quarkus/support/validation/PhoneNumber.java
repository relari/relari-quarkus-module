package pe.com.relari.fwk.quarkus.support.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.*;

import static pe.com.relari.commons.constant.Regex.REGEXP_PHONE_NUMBER;

/**
 * annotation: PhoneNumber.
 *
 * @author Relari
 */

@Documented
@Constraint(validatedBy = PhoneNumber.PhoneNumberImpl.class) // Enlace con la lógica
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {

    String message() default "Value is not valid for the required category";

    class PhoneNumberImpl implements ConstraintValidator<PhoneNumber, String> {

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null || value.isBlank()) {
                return true;
            }
            return value.matches(REGEXP_PHONE_NUMBER);
        }
    }

}