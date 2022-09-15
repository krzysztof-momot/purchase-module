package com.example.repositories.jpa.entities;

import com.example.domain.CreditCard;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    private BigDecimal limit;

    @Column(name = "number")
    private String number;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creditCard")
    private List<ChargeJPA> charges = new ArrayList<>();

    public CreditCard toCreditCard() {
        return new CreditCard(number, limit, charges.stream()
                .map(ChargeJPA::getAmount)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
    }
}



