package com.v2.product.domain.model;

import com.v2.inventory.domain.model.Inventory;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Getter
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    // 1:1 연관관계 (지연 로딩)
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Inventory inventory;

    protected Product() {
        this.name = null;
        this.description = null;
        this.price = BigDecimal.ZERO;
    }

    public Product(String name, String description, BigDecimal price) {
        if (name == null || price == null) {
            throw new IllegalArgumentException("Name과 Price는 null일 수 없습니다.");
        }
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // 상품 가격 변경 메서드
    public void changePrice(BigDecimal newPrice) {
        if (newPrice == null || newPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("가격은 0보다 커야 합니다.");
        }
        this.price = newPrice;
    }

    // 상품 설명 변경 메서드
    public void changeDescription(String newDescription) {
        if (newDescription == null || newDescription.isEmpty()) {
            throw new IllegalArgumentException("설명은 null이거나 비어있을 수 없습니다.");
        }
        this.description = newDescription;
    }

    // 상품 이름 변경 메서드
    public void changeName(String newName) {
        if (newName == null || newName.isEmpty()) {
            throw new IllegalArgumentException("이름은 null이거나 비어있을 수 없습니다.");
        }
        this.name = newName;
    }

    // 연관관계 설정을 위한 비즈니스 메서드
    public void assignInventory(Inventory inventory) {
        this.inventory = inventory;
        inventory.attachProduct(this);
    }

    @Builder
    public Product(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}

