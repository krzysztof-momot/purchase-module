package com.example.domain;

/**
 * Charge repository.
 */
public interface ChargeRepository {
    /**
     * Save charge into persistence module.
     *
     * @param charge to save
     */
    void save(Charge charge);
}
