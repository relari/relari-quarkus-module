package pe.com.relari.fwk.quarkus.support.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.*;

import static pe.com.relari.commons.constant.Regex.REGEXP_DATE;

/**
 * annotation: DateSimple.
 *
 * @author Relari
 */

@Documented
@Constraint(validatedBy = DateSimple.DateSimpleImpl.class) // Enlace con la lógica
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateSimple {

    String message() default "Value is not valid for the required category";

    class DateSimpleImpl implements ConstraintValidator<DateSimple, String> {

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null || value.isBlank()) {
                return true;
            }
            return value.matches(REGEXP_DATE);
        }
    }

}