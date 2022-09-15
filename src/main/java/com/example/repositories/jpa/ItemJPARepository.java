package com.example.repositories.jpa;

import com.example.repositories.jpa.entities.ItemJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemJPARepository extends JpaRepository<ItemJPA, String> {

}