package com.v1.product.api;

import com.v1.product.api.dto.request.CreateProductRequest;
import com.v1.product.api.dto.response.ProductQueryResponse;
import com.v1.product.api.mapper.ProductCommandMapper;
import com.v1.product.api.mapper.ProductResponseMapper;
import com.v1.product.application.dto.command.CreateProductCommand;
import com.v1.product.application.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductCommandMapper productCommandMapper;
    private final ProductResponseMapper productResponseMapper;

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<ProductQueryResponse>> getProductList() {
        return ResponseEntity.ok().body(
                productResponseMapper.productQueryToResponse(
                        productService.getProductList()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductQueryResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok().body(
                productService.getProductById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(
            @PathVariable Long id,
            @RequestBody @Valid final CreateProductRequest updateProductRequest
    ) {
        CreateProductCommand command = productCommandMapper.createProductRequestToCommand(updateProductRequest);
        productService.updateProduct(id, command);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> createProduct(
            @RequestBody @Valid final CreateProductRequest createProductRequest
    ) {
        CreateProductCommand command = productCommandMapper.createProductRequestToCommand(createProductRequest);
        productService.createProduct(command);

        return ResponseEntity.created(URI.create("/product")).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // 상품 검색 API 는 Redis를 사용하여 캐싱을 진행
    @GetMapping("/search")
    public ResponseEntity<List<ProductQueryResponse>> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) {
        return ResponseEntity.ok().body(
                productResponseMapper.productQueryToResponse(
                        productService.searchProducts(name, category, minPrice, maxPrice)
                )
        );
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkProductExists(@PathVariable Long id) {
        boolean exists = productService.checkProductExists(id);
        return ResponseEntity.ok(exists);
    }
}
