package com.example.domain;

import io.vavr.collection.HashMap;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseServiceTest {
    private final PurchaseService purchaseService = new PurchaseService();

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void makePurchaseItemsWithinCardLimit(int quantity) {

        BigDecimal cost = BigDecimal.valueOf(899.5);
        Item item = new Item("TV", cost);

        CreditCard creditCard = new CreditCard("abc", BigDecimal.valueOf(5000), BigDecimal.ZERO);

        Purchase purchase = purchaseService.makePurchase(creditCard, HashMap.of(item, quantity));

        assertThat(purchase.getCharge()).isInstanceOf(Charge.Accepted.class);
        assertThat(purchase.getCharge().getAmount()).isEqualTo(cost.multiply(BigDecimal.valueOf(quantity)));

    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void makePurchaseItemsExceedingCardLimit(int quantity) {

        BigDecimal cost = BigDecimal.valueOf(1799.5);
        Item item = new Item("PC", cost);

        CreditCard creditCard = new CreditCard("abc", BigDecimal.valueOf(1000), BigDecimal.ZERO);

        Purchase purchase = purchaseService.makePurchase(creditCard, HashMap.of(item, quantity));

        assertThat(purchase.getCharge()).isInstanceOf(Charge.Rejected.class);
        assertThat(purchase.getCharge().getAmount()).isEqualTo(cost.multiply(BigDecimal.valueOf(quantity)));
        assertThat(purchase.getCharge().getMessage()).isEqualTo("Credit card limit exceeded.");
    }
}