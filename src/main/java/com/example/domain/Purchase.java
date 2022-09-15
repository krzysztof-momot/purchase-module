package com.example.domain;

import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Purchase {
    private final Map<Item, Integer> items;
    private final Charge charge;

    public static Purchase rejected(Charge.Rejected charge) {
        return new Purchase(HashMap.empty(), charge);
    }
}
