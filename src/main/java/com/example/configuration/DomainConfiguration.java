package com.example.configuration;

import com.example.domain.ChargeRepository;
import com.example.domain.PurchaseService;
import com.example.repositories.ChargeRepositoryImpl;
import com.example.repositories.jpa.ChargeJPARepository;
import com.example.repositories.jpa.CreditCardJPARepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public PurchaseService getPurchaseService() {
        return new PurchaseService();
    }

    @Bean
    public ChargeRepository getChargeRepository(ChargeJPARepository chargeJPARepository, CreditCardJPARepository creditCardJPARepository) {
        return new ChargeRepositoryImpl(chargeJPARepository, creditCardJPARepository);
    }

}
