package com.stu.valied.excption;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



public class TestParamValied implements ConstraintValidator<StringParamValied, String> {


    @Override
    public void initialize(StringParamValied constraintAnnotation) {


    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
