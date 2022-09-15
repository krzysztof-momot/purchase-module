package com.example.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Charge {

    private final CreditCard creditCard;
    private final double amount;
    private final String message;


    static class Accepted extends Charge {
        Accepted(CreditCard creditCard, double amount, String message) {
            super(creditCard, amount, message);
        }
    }

    static class Rejected extends Charge {
        Rejected(CreditCard creditCard, double amount, String message) {
            super(creditCard, amount, message);
        }
    }
}
