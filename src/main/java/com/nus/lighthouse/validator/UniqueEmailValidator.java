package com.nus.lighthouse.validator;

import com.nus.lighthouse.annotation.UniqueEmail;
import com.nus.lighthouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(UniqueEmail constrainAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return userService.checkEmailExists(email);
    }
}
