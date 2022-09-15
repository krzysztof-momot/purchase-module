package com.example.api;


import com.example.api.purchase.ItemDTO;
import com.example.domain.Purchase;
import io.vavr.collection.Map;
import lombok.Value;

@Value
public class PurchaseTO {

    Map<ItemDTO, Integer> purchasedItems;
    double chargeAmount;
    String message;

    public static PurchaseTO from(Purchase purchase) {
        return new PurchaseTO(purchase.getItems().mapKeys(ItemDTO::from), purchase.getCharge().getAmount(), purchase.getCharge().getMessage());
    }
}
