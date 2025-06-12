package org.blacksage.learn.easyschool.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.blacksage.learn.easyschool.validations.PasswordValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

        // Mandatory boilerplate code
        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

        String message() default "Password is weak";
}
