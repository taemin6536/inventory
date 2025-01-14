package com.v1.product.domain.entity;

import com.v1.product.domain.model.ProductCode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
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
}
