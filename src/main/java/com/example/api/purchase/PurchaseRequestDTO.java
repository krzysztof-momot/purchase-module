package com.example.api.purchase;

import io.vavr.collection.List;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class PurchaseRequestDTO {
    @NotNull CreditCardDTO creditCard;
    @NotNull List<ItemDTO> items;
}
