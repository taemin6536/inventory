package com.v2.inventory.api;

import com.v2.inventory.api.dto.req.UpdateInventoryRequest;
import com.v2.inventory.application.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PutMapping("/{id}/inventory")
    public ResponseEntity<Void> updateInventory(
            @PathVariable final Long id,
            @RequestBody final UpdateInventoryRequest request
    ) {
        inventoryService.updateInventory(id, request);
        return ResponseEntity.noContent().build();
    }
}
