package com.example.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

/**
 * Item business object.
 */
@Getter
@RequiredArgsConstructor
public class Item {

    private final String id;
    private final BigDecimal cost;
}
