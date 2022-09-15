package com.example.repositories.jpa.entities;

import com.example.domain.Charge;
import com.example.domain.CreditCard;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "CreditCard")
public class CreditCardJPA {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "limit")
    private Double limit;

    @Column(name = "number")
    private String number;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creditCard")
    private List<ChargeJPA> charges = new ArrayList<>();

    public CreditCard toCreditCard() {
        return new CreditCard(number, limit, charges.stream()
                .map(ChargeJPA::getAmount)
                .reduce(Double::sum).orElse(0.0));
    }
}



