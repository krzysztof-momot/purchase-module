package com.example.repositories.jpa;

import com.example.repositories.jpa.entities.ChargeJPA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ChargeJPARepository extends CrudRepository<ChargeJPA, Long> {
}
