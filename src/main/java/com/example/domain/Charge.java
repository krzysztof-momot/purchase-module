package com.example.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public class Charge {

    private final CreditCard creditCard;
    private final BigDecimal amount;
    private final String message;


    static class Accepted extends Charge {
        Accepted(CreditCard creditCard, BigDecimal amount, String message) {
            super(creditCard, amount, message);
        }
    }

    static class Rejected extends Charge {
        Rejected(CreditCard creditCard, BigDecimal amount, String message) {
            super(creditCard, amount, message);
        }
    }
}
