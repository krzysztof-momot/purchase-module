package com.example.repositories.jpa.entities;

import com.example.domain.Charge;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "Charge")
public class ChargeJPA {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "credit_card_id")
    private CreditCardJPA creditCard;

    @Column
    private double amount;

    public static ChargeJPA from(Charge charge, CreditCardJPA creditCardJPA) {
        return ChargeJPA.builder()
                .creditCard(creditCardJPA)
                .amount(charge.getAmount())
                .build();
    }
}
