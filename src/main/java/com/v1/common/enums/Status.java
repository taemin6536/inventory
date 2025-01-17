package com.v1.common.enums;

public enum Status {
    ACTIVE("판매중"),
    INACTIVE("판매중지");

    private String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
