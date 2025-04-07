package com.v2.inventory.infra.adapter;

import com.v2.inventory.domain.model.Inventory;
import com.v2.inventory.domain.repository.InventoryProduct;
import com.v2.inventory.infra.repository.InventoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class InventoryRepositoryAdapter implements InventoryProduct {
     private final InventoryJpaRepository inventoryJpaRepository;
    // private final InventoryMybatisRepository inventoryMybatisRepository;
    @Override
    public Optional<Inventory> findById(final Long id) {
        return inventoryJpaRepository.findById(id);
    }

    // @Override
    // public List<Inventory> findAll() {
    //     return inventoryJpaRepository.findAll();
    // }
    //
    // @Override
    // public void save(Inventory inventory) {
    //     inventoryJpaRepository.save(inventory);
    // }
}
