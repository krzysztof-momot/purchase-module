package com.example.domain;

import io.vavr.collection.Map;
import io.vavr.control.Either;

public class PurchaseService {

    private final ChargeRepository chargeRepository;

    public PurchaseService(ChargeRepository chargeRepository) {
        this.chargeRepository = chargeRepository;
    }

    public Purchase makePurchase(CreditCard creditCard, Map<Item, Integer> itemsToQuantity) {
        Either<Charge.Rejected, Charge.Accepted> result = creditCard.accept(itemsToQuantity);
        if (isRejected(result)) {
            return Purchase.rejected(result.getLeft());
        }
        Charge.Accepted charge = result.get();
        chargeRepository.save(charge);
        return new Purchase(itemsToQuantity, charge);
    }

    private boolean isRejected(Either<Charge.Rejected, Charge.Accepted> result) {
        return result.isLeft();
    }
}
