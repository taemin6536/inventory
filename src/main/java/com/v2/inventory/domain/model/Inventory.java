package com.v2.inventory.domain.model;

import com.v2.product.domain.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "inventory")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Product 와 1:1 매핑
    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer availableQuantity;

    @Column(nullable = false)
    private Integer reservedQuantity;


    public Inventory(Integer availableQuantity, Integer reservedQuantity) {
        if (availableQuantity == null || reservedQuantity == null) {
            throw new IllegalArgumentException("재고 수량은 null일 수 없습니다.");
        }
        this.availableQuantity = availableQuantity;
        this.reservedQuantity = reservedQuantity;
    }

    // 연관관계 설정 (캡슐화)
    public void attachProduct(Product product) {
        this.product = product;
    }

    // 비즈니스 메서드: 재고 감소
    public void decreaseAvailableQuantity(int quantity) {
        if (quantity <= 0 || availableQuantity < quantity) {
            throw new IllegalArgumentException("유효하지 않은 감소 수량입니다.");
        }
        this.availableQuantity -= quantity;
    }

    // 비즈니스 메서드: 재고 증가
    public void increaseAvailableQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("유효하지 않은 증가 수량입니다.");
        }
        this.availableQuantity += quantity;
    }

    // 비즈니스 메서드: 예약 진행 (가용 재고 감소, 예약 재고 증가)
    public void reserveStock(int quantity) {
        if (quantity <= 0 || availableQuantity < quantity) {
            throw new IllegalArgumentException("예약할 재고가 부족합니다.");
        }
        this.availableQuantity -= quantity;
        this.reservedQuantity += quantity;
    }

    // 비즈니스 메서드: 예약 해제 (예약 재고 감소, 가용 재고 증가)
    public void releaseReservedStock(int quantity) {
        if (quantity <= 0 || reservedQuantity < quantity) {
            throw new IllegalArgumentException("해제할 예약 재고가 부족합니다.");
        }
        this.reservedQuantity -= quantity;
        this.availableQuantity += quantity;
    }
}
