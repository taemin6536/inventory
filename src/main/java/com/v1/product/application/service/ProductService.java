package com.v1.product.application.service;

import com.v1.product.application.dto.command.CreateProductCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    public void createProduct(
            final CreateProductCommand createProductCommand
    ) {

    }
}
