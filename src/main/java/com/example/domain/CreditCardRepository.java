package com.example.domain;

import io.vavr.control.Option;

/**
 * Repository for credit card.
 */
public interface CreditCardRepository {
    /**
     * Get credit card by card number.
     *
     * @param cardNumber card number
     * @return an object containing credit card if one was found or being empty otherwise
     */
    Option<CreditCard> get(String cardNumber);
}
