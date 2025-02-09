package com.v1.product.domain.entity;

import com.v1.global.common.entity.BaseEntity;
import com.v1.global.common.enums.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Entity
@Getter
@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(nullable = false, name = "product_name")
    private String name;

    @Column(columnDefinition = "text", name = "product_description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "category")
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "product_status")
    private Status status;

    @Column(nullable = false, name = "price")
    private int price;

    @Builder
    public Product(String name, String description, Category category, int price) {
        validateProductCode(name, description);
        this.category = category;
        this.name = name;
        this.description = description;
        this.status = Status.ACTIVE;
        this.price = price;
    }

    private void validateProductCode(String name, String description) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("상품 이름은 필수입니다.");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("상품 설명은 필수입니다.");
        }
    }

    //비지니스 로직
    public void updateInfo(String name, String description) {
        validateUpdateInfo(name, description);
        this.name = name;
        this.description = description;
    }

    private void validateUpdateInfo(String name, String description) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("상품 이름은 필수입니다.");
        }
        if (!StringUtils.hasText(description)) {
            throw new IllegalArgumentException("상품 설명은 필수입니다.");
        }
    }

    public void deactivate() {
        this.status = Status.INACTIVE;
    }

    public void activate() {
        this.status = Status.ACTIVE;
    }

    public boolean isActive() {
        return this.status == Status.ACTIVE;
    }

}
