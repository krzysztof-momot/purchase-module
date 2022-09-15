package com.example.domain;

import io.vavr.collection.Map;
import io.vavr.control.Either;

public class PurchaseService {
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
