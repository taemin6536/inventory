package com.v1.product.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Category {
    ELECTRONICS("전자기기"),
    FASHION("패션"),
    FOOD("식품"),
    BEAUTY("뷰티"),
    HOME_LIVING("생활용품"),
    PET("반려동물");

    private final String value;

    public static Category from(String value) {
        return Arrays.stream(values())
                .filter(c -> c.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 카테고리입니다."));
    }
}
