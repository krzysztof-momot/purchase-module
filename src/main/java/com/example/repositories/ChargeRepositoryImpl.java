package com.example.repositories;

import com.example.repositories.jpa.ChargeJPARepository;
import com.example.repositories.jpa.CreditCardJPARepository;
import com.example.repositories.jpa.entities.ChargeJPA;
import com.example.domain.Charge;
import com.example.domain.ChargeRepository;
import com.example.repositories.jpa.entities.CreditCardJPA;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ChargeRepositoryImpl implements ChargeRepository {

    private final ChargeJPARepository chargeJPARepository;
    private final CreditCardJPARepository creditCardJPARepository;

    @Override
    public void save(Charge charge) {
        Optional<CreditCardJPA> creditCardJPA = creditCardJPARepository.findByNumber(charge.getCreditCard().getNumber());
        chargeJPARepository.save(ChargeJPA.from(charge, creditCardJPA.get()));
    }
}
