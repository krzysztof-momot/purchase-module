package com.example.domain;

import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Purchase business object.
 */
@RequiredArgsConstructor
@Getter
public class Purchase {
    private final Map<Item, Integer> items;
    private final Charge charge;

    /**
     * Method to create representation of empty, rejected purchase.
     *
     * @param charge rejected charge
     * @return empty purchase with rejected charge
     */
    public static Purchase rejected(Charge.Rejected charge) {
        return new Purchase(HashMap.empty(), charge);
    }
}
