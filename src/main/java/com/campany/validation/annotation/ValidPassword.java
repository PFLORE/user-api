package com.campany.validation.annotation;

import com.campany.validation.validator.CustomPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CustomPasswordValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Contraseña inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
