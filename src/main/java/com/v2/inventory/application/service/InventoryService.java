package com.v2.inventory.application.service;

import com.v2.common.annotation.OptimisticRetry;
import com.v2.inventory.api.dto.req.UpdateInventoryRequest;
import com.v2.inventory.domain.repository.InventoryProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryProduct inventoryProduct;

    // 아래 로직 사용자 어노테이션으로 대체 or @Retry() 도 있음
//    public void updateInventoryWithRetry(
//            final Long id,
//            final UpdateInventoryRequest request
//    ) {
//        int retryCount = 3;
//        for (int i = 0; i < retryCount; i++) {
//            try {
//                // 트랜잭션 내부에서 업데이트를 시도
//                updateInventory(id, request);
//                return;
//            } catch (OptimisticLockException e) {
//                // 낙관적 락 충돌 발생 -> 재시도
//                if (i == retryCount - 1) {
//                    throw new IllegalStateException("재고 업데이트 충돌이 너무 자주 발생함.", e);
//                }
//            }
//        }
//    }

    // 상품 재고 수량 업데이트
    @Transactional
    @OptimisticRetry(
            maxAttempts = 3,
            delay = 1000
    )
    public void updateInventory(
            final Long id,
            final UpdateInventoryRequest request
    ) {
        inventoryProduct.findById(id)
                .ifPresentOrElse(
                        inventory -> {
                            inventory.updateAvailableStock(request.availableQuantity());
                        },
                        () -> {
                            throw new IllegalArgumentException("상품을 찾을 수 없음 id: " + id);
                        }
                );
    }
}
