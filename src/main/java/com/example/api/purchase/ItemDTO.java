package com.example.api.purchase;

import com.example.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ItemDTO {
    private String id;

    public static ItemDTO from(Item item) {
        return new ItemDTO(item.getId());
    }
}
