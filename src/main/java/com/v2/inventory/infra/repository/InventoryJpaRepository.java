package com.v2.inventory.infra.repository;

import com.v2.inventory.domain.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryJpaRepository extends JpaRepository<Inventory, Long> {
    boolean existsByProductId(Long productId);
    boolean existsByProductIdAndQuantityGreaterThan(Long productId, Integer quantity);
    Inventory findByProductId(Long productId);
}
