package org.blacksage.learn.easyschool.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.blacksage.learn.easyschool.validations.FieldsValueMatchValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueMatch {

        // Mandatory boilerplate code
        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

        String message() default "Fields value mismatch";

        String fieldA();

        String fieldB();

        @Target({ElementType.TYPE})
        @Retention(RetentionPolicy.RUNTIME)
        @interface List {
                FieldsValueMatch[] value();
        }
}
