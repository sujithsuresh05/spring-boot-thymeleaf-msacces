package com.nagarro.banking.validator;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class AmountValueMatchValidator implements ConstraintValidator<AmountValueMatch, Object> {

    private String feild;

    private String feildMatch;

    @Override
    public void initialize(AmountValueMatch constraintAnnotation) {
        this.feild = constraintAnnotation.field();
        this.feildMatch = constraintAnnotation.fieldMatch();
    }


    @Override
    public boolean isValid(Object value,
                           ConstraintValidatorContext context) {

        Object fieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(feild);
        Object fieldMatchValue = new BeanWrapperImpl(value)
                .getPropertyValue(feildMatch);

        if (fieldValue != null && fieldMatchValue != null) {
            Integer amountFrom = new Integer((String) fieldValue);
            Integer amountTo = new Integer((String) fieldValue);
            return amountFrom.intValue() <= amountTo.intValue();
        } else {
            return fieldMatchValue == null;
        }
    }
}
