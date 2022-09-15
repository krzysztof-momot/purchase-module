package com.example.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Item {

    private final String id;
    private final double cost;
}
