package com.example.domain;

import io.vavr.collection.Map;
import io.vavr.control.Either;

/**
 * Service for making {@link Purchase}.
 */
public class PurchaseService {
    /**
     * @param creditCard      Credit card used to make purchase
     * @param itemsToQuantity map containing items and their quantities to buy
     * @return a Purchase, either accepted or rejected
     */
    public Purchase makePurchase(CreditCard creditCard, Map<Item, Integer> itemsToQuantity) {
        Either<Charge.Rejected, Charge.Accepted> result = creditCard.accept(itemsToQuantity);
        if (isRejected(result)) {
            return Purchase.rejected(result.getLeft());
        }
        return new Purchase(itemsToQuantity, result.get());
    }

    private boolean isRejected(Either<Charge.Rejected, Charge.Accepted> result) {
        return result.isLeft();
    }
}
