package pe.com.relari.commons.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import pe.com.relari.commons.validation.impl.EnumValidatorImpl;

import java.lang.annotation.*;

/**
 * annotation: EnumValidator.
 *
 * @author Relari
 */

@Documented
@Constraint(validatedBy = EnumValidatorImpl.class) // Enlace con la lógica
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValidator {
  String message() default "Value is not valid for the required category";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}