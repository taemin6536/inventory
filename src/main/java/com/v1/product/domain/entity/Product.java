package com.v1.product.domain.entity;

import com.v1.common.entity.BaseEntity;
import com.v1.common.enums.Status;
import com.v1.product.domain.model.ProductCode;
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

    @Embedded
    private ProductCode productCode;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Builder
    public Product(ProductCode productCode, String name, String description) {
        validateProductCode(productCode, name, description);
        this.productCode = productCode;
        this.name = name;
        this.description = description;
    }

    private void validateProductCode(ProductCode productCode, String name, String description) {
        if (productCode == null) {
            throw new IllegalArgumentException("상품 코드는 필수입니다.");
        }
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
