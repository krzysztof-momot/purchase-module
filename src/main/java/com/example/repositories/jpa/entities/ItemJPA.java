package com.example.repositories.jpa.entities;

import com.example.domain.Item;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "Item")
public class ItemJPA {

    @Id
    private String id;

    @Column
    private double cost;

    public Item toItem() {
        return new Item(id, cost);
    }
}
