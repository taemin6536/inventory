package com.v2.product.application.service;

import com.v2.product.api.dto.req.CreateProductRequest;
import com.v2.product.api.dto.req.UpdateProductRequest;
import com.v2.product.api.dto.res.ProductResponse;
import com.v2.product.application.mapper.ProductMapper;
import com.v2.product.domain.model.Product;
import com.v2.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public void createProduct(
            final CreateProductRequest request
    ) {
        // 중복 체크: 이미 같은 이름의 제품이 존재하는지 확인 ㄱㄱ
        if (productRepository.existsByName(request.name())) {
            throw new IllegalArgumentException("상품 이미 존재함 name: " + request.name());
        }

        final Product product = productMapper.toProduct(request);
        productRepository.save(product);
    }

    public ProductResponse getProductById(
            final Long id
    ) {
        return productRepository.findById(id)
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()
                ))
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없음 id: " + id));
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()
                ))
                .toList();
    }

    public ProductResponse updateProduct(
            final Long id,
            final UpdateProductRequest request
    ) {
        return productRepository.findById(id)
                .map(product -> {
                    product.changeName(request.name());
                    product.changeDescription(request.description());
                    product.changePrice(request.price());
                    return new ProductResponse(
                            product.getId(),
                            product.getName(),
                            product.getDescription(),
                            product.getPrice()
                    );
                })
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없음 id: " + id));
    }
}
