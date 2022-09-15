package com.example.domain;

/**
 * Repository for items.
 */
public interface ItemRepository {

    /**
     * Get item by id.
     *
     * @param id id of item
     * @return found item
     */
    Item get(String id);
}
