package com.v3;

import org.jspecify.annotations.Nullable;

public class Calculator {
    public Integer add(Integer a, Integer b) {
        return a + b;
    }

    // 파라미터 B는 널 가능이죠?
    public Integer minus(Integer a, @Nullable Integer b) {
        return a + (b == null ? 0 : b);
    }
}