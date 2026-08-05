package pe.com.relari.fwk.quarkus.support.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.*;

import static pe.com.relari.commons.constant.Regex.REGEXP_ONLY_NUMBER;

/**
 * annotation: NumericOnly.
 *
 * @author Relari
 */

@Documented
@Constraint(validatedBy = NumericOnly.NumericOnlyImpl.class) // Enlace con la lógica
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumericOnly {

  String message() default "Value is not valid for the required category";

  class NumericOnlyImpl implements ConstraintValidator<NumericOnly, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      if (value == null || value.isBlank()) {
        return true;
      }
      return value.matches(REGEXP_ONLY_NUMBER);
    }
  }

}