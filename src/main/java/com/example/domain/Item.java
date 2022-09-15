package com.example.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Item business object.
 */
@Getter
@RequiredArgsConstructor
public class Item {

    private final String id;
    private final double cost;
}
