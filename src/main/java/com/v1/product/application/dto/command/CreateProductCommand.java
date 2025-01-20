package com.v1.product.application.dto.command;

public record CreateProductCommand (
        String name,
        String description
){}
