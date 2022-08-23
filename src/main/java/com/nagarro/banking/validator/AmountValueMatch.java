package com.nagarro.banking.validator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;

@Constraint(validatedBy = AmountValueMatchValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AmountValueMatch {

    String message() default "Fields values don't match!";

    String field();

    String fieldMatch();

}
