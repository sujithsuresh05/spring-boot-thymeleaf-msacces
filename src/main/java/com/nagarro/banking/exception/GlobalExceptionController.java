package com.nagarro.banking.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import static com.nagarro.banking.utils.Constants.*;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(InvalidAccountException.class)
    public ModelAndView userNotFoundException(InvalidAccountException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(GLOBAL_ERROR_NAME, INVALID_ACCOUNT_ERROR_MSG);
        modelAndView.setViewName(VALID_USER_ERROR_VIEW);
        return modelAndView;
    }

}
