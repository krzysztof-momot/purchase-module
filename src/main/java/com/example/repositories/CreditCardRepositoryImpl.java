package com.example.repositories;

import com.example.repositories.jpa.CreditCardJPARepository;
import com.example.domain.CreditCard;
import com.example.domain.CreditCardRepository;
import com.example.repositories.jpa.entities.CreditCardJPA;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreditCardRepositoryImpl implements CreditCardRepository {

    private final CreditCardJPARepository creditCardJPARepository;

    @Autowired
    public CreditCardRepositoryImpl(CreditCardJPARepository creditCardJPARepository) {
        this.creditCardJPARepository = creditCardJPARepository;
    }

    @Override
    public Option<CreditCard> get(String cardNumber) {
        //TODO maybe cache?
        Optional<CreditCardJPA> creditCardJPA = creditCardJPARepository.findByNumber(cardNumber);
        if (creditCardJPA.isPresent()) {
            return Option.of(creditCardJPA.get().toCreditCard());
        }
        return Option.none();
    }
}
