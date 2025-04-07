package com.v2.product.application.service;

import com.v2.inventory.domain.model.Inventory;
import com.v2.product.api.dto.req.CreateProductRequest;
import com.v2.product.api.dto.req.UpdateProductRequest;
import com.v2.product.api.dto.res.ProductResponse;
import com.v2.product.application.mapper.ProductMapper;
import com.v2.product.domain.model.Product;
import com.v2.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        // 초기 인벤토리 생성 (예: 가용재고와 예약 재고를 모두 0으로 초기화)
        Inventory inventory = new Inventory(0, 0);
        product.assignInventory(inventory);

        productRepository.save(product);
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
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

    @Transactional
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

    public void deleteProduct(
            final Long id
    ) {
        productRepository.findById(id)
                .ifPresentOrElse(
                        Product::deleted,
                        () -> {
                            throw new IllegalArgumentException("상품을 찾을 수 없음 id: " + id);
                        }
                );

    }

}
