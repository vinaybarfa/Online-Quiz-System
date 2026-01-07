package com.vinay.OnlineQuizapp.CustomAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<Phone, String> {
    private static final String PHONE_PATTEN = "^(\\+91|91|0)?[6-9]\\d{9}$";
    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null || phoneNumber.isBlank()){
            return false;
        }
        return phoneNumber.matches(PHONE_PATTEN);
    }
}
