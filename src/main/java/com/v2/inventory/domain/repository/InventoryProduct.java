package com.v2.inventory.domain.repository;

import com.v2.inventory.domain.model.Inventory;

import java.util.Optional;

public interface InventoryProduct {
    Optional<Inventory> findById(Long id);
}
