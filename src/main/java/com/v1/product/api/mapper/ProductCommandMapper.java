package com.v1.product.api.mapper;

import com.v1.product.api.dto.request.CreateProductRequest;
import com.v1.product.application.dto.command.CreateProductCommand;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class ProductCommandMapper {

    public CreateProductCommand createProductRequestToCommand(
            @Valid CreateProductRequest createProductRequest
    ) {
        return new CreateProductCommand(
                createProductRequest.name(),
                createProductRequest.description()

        );
    }
}
