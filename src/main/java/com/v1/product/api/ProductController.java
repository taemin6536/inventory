package com.v1.product.api;

import com.v1.product.api.mapper.ProductCommandMapper;
import com.v1.product.api.dto.request.CreateProductRequest;
import com.v1.product.api.dto.response.ProductQueryResponse;
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

    @PostMapping(value = "/create")
    public ResponseEntity<Void> createProduct(
            @RequestBody @Valid final CreateProductRequest createProductRequest
    ) {
        CreateProductCommand command = productCommandMapper.createProductRequestToCommand(createProductRequest);
        productService.createProduct(command);

        return ResponseEntity.created(URI.create("/product")).build();
    }
}
