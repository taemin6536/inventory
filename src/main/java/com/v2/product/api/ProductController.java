package com.v2.product.api;

import com.v2.product.api.dto.req.CreateProductRequest;
import com.v2.product.api.dto.req.UpdateProductRequest;
import com.v2.product.api.dto.res.ProductResponse;
import com.v2.product.application.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // 제품 등록: POST /products
    @PostMapping
    public ResponseEntity<Void> createProduct(
            @RequestBody @Valid final CreateProductRequest request
    ) {
         productService.createProduct(request);
        return ResponseEntity.created(URI.create("/products")).build();
    }

    // 제품 상세 조회: GET /products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(
            @PathVariable final Long id
    ) {
        ProductResponse response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    // 전체 제품 목록 조회: GET /products
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> responses = productService.getAllProducts();
        return ResponseEntity.ok(responses);
    }

    // 제품 수정: PUT /products/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable final Long id, @RequestBody final UpdateProductRequest request
    ) {
        ProductResponse response = productService.updateProduct(id, request);
        return ResponseEntity.ok(response);
    }

    // 제품 삭제: DELETE /products/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable final Long id
    ) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
