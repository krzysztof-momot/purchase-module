package com.example.application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Invalid credit card")
public class InvalidCreditCardException extends RuntimeException {

    public InvalidCreditCardException(String cardNumber) {
        super(cardNumber);
    }
}
