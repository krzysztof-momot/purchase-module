package com.example.api;


import com.example.api.purchase.ItemDTO;
import com.example.domain.Purchase;
import io.vavr.collection.Map;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class PurchaseDTO {

    Map<ItemDTO, Integer> purchasedItems;
    BigDecimal chargeAmount;
    String message;

    public static PurchaseDTO from(Purchase purchase) {
        return new PurchaseDTO(purchase.getItems().mapKeys(ItemDTO::from),
                purchase.getCharge().getAmount(),
                purchase.getCharge().getMessage());
    }
}
