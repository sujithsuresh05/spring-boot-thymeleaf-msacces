package com.nagarro.banking.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class InvalidAccountException extends IllegalArgumentException{

    private final Long accountId;

    private final String message;

}
