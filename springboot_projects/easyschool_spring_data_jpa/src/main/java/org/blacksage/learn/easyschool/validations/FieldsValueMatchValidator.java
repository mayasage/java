package org.blacksage.learn.easyschool.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.blacksage.learn.easyschool.annotations.FieldsValueMatch;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

public class FieldsValueMatchValidator implements
        ConstraintValidator<FieldsValueMatch, Object> {

        private String fieldA;
        private String fieldB;

        @Override
        public void initialize(FieldsValueMatch constraintAnnotation) {
                fieldA = constraintAnnotation.fieldA();
                fieldB = constraintAnnotation.fieldB();
        }

        @Override
        public boolean isValid(Object fieldValueMatchData,
                               ConstraintValidatorContext context) {
                BeanWrapper beanWrapper =
                        PropertyAccessorFactory
                                .forBeanPropertyAccess(fieldValueMatchData);
                String fieldAValue =
                        beanWrapper
                                .convertIfNecessary(
                                        beanWrapper.getPropertyValue(fieldA),
                                        String.class
                                );
                String fieldBValue =
                        beanWrapper
                                .convertIfNecessary(
                                        beanWrapper.getPropertyValue(fieldB),
                                        String.class
                                );
                if (fieldAValue == null) {
                        return fieldBValue == null;
                } else if (fieldBValue == null) {
                        // fieldA is not null, fieldB is null.
                        return false;
                } else {
                        // Both fields are not null.
                        return fieldAValue.equals(fieldBValue);
                }
        }
}
