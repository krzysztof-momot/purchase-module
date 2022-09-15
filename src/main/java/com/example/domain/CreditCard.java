package com.example.domain;

import io.vavr.Tuple;
import io.vavr.collection.Map;
import io.vavr.control.Either;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * CreditCard business object.
 */
@Getter
@RequiredArgsConstructor
public class CreditCard {

    private final String number;
    private final double limit;

    private final double totalCharges;

    /**
     * Accept 'shopping list', i.e. items to buy.
     *
     * @param itemsToQuantity items to buy and their quantities
     * @return a charge, either accepted or rejected
     */
    Either<Charge.Rejected, Charge.Accepted> accept(Map<Item, Integer> itemsToQuantity) {
        double newCharge = itemsToQuantity.map((item, quantity) -> Tuple.of(item, item.getCost() * quantity))
                .values()
                .sum()
                .doubleValue();
        return totalCharges + newCharge > limit ? Either.left(new Charge.Rejected(this, newCharge, "Credit card limit exceeded."))
                : Either.right(new Charge.Accepted(this, newCharge, "Success."));
    }
}
