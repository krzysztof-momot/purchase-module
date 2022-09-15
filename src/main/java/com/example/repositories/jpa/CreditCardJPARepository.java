package com.example.repositories.jpa;

import com.example.repositories.jpa.entities.CreditCardJPA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditCardJPARepository extends CrudRepository<CreditCardJPA, Long> {

    Optional<CreditCardJPA> findByNumber(String number);
}