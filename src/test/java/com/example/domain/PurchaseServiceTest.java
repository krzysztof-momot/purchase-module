package com.example.domain;

import io.vavr.collection.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PurchaseServiceTest {
    private PurchaseService purchaseService;

    @Mock
    private ChargeRepository chargeRepository;

    @BeforeEach
    void setup() {
        purchaseService = new PurchaseService(chargeRepository);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void makePurchaseItemsWithinCardLimit(int quantity) {

        double cost = 899.5;
        Item item = new Item("TV", cost);

        CreditCard creditCard = new CreditCard("abc", 5000, 0);

        Purchase purchase = purchaseService.makePurchase(creditCard, HashMap.of(item, quantity));

        assertThat(purchase.getCharge()).isInstanceOf(Charge.Accepted.class);
        assertThat(purchase.getCharge().getAmount()).isEqualTo(cost * quantity);

        verify(chargeRepository).save(purchase.getCharge());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void makePurchaseItemsExceedingCardLimit(int quantity) {

        double cost = 1799.5;
        Item item = new Item("PC", cost);

        CreditCard creditCard = new CreditCard("abc", 1000, 0);

        Purchase purchase = purchaseService.makePurchase(creditCard, HashMap.of(item, quantity));

        assertThat(purchase.getCharge()).isInstanceOf(Charge.Rejected.class);
        assertThat(purchase.getCharge().getAmount()).isEqualTo(cost * quantity);
        assertThat(purchase.getCharge().getMessage()).isEqualTo("Credit card limit exceeded.");
    }
}