package com.example.domain;

import io.vavr.control.Option;

public interface CreditCardRepository {
    Option<CreditCard> get(String cardNumber);
}
