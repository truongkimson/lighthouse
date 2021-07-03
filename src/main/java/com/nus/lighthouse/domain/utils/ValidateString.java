package com.nus.lighthouse.domain.utils;

import javax.validation.Payload;

public @interface ValidateString {
    String[] acceptedValues();

    String message() default "{com.nus.lighthouse.domain.utils.ValidateString.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { }; 
}
