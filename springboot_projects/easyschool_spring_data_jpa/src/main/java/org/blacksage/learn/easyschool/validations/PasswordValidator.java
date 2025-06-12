package org.blacksage.learn.easyschool.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.blacksage.learn.easyschool.annotations.Password;

import java.util.Arrays;
import java.util.List;

public class PasswordValidator implements
        ConstraintValidator<Password, String> {

        private final List<String> weakPasswords =
                Arrays.asList("12345", "password", "qwerty");

        @Override
        public boolean isValid(String password,
                               ConstraintValidatorContext context) {
                return password != null && !weakPasswords.contains(password);
        }
}
