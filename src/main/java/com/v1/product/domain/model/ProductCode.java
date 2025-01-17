package com.v1.product.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/*
*  Product 내에 들어가는 속성으로 VO와 Entity 분리하여 캡슐화 적용
*  code 규칙은 ( 네글자-000x ) 로 진행
*/
@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductCode {
    @Column(name = "product_code", nullable = false)
    private String value;

    public ProductCode(String value) {
        validateProductCode(value);
        this.value = value;
    }

//    유효성 검사
    private void validateProductCode(String value) {
        if (!StringUtils.hasText(value)) {
            throw new IllegalArgumentException("상품 코드는 필수입니다.");
        }
        if (value.length() > 20) {
            // TODO 임시로 지정함 일단
            throw new IllegalArgumentException("상품 코드는 20자를 초과할 수 없습니다.");
        }
        if (!value.matches("^[A-Z0-9-]+$")) {
            throw new IllegalArgumentException("상품 코드는 대문자, 숫자, - 만 사용 가능합니다.");  // code명은 food-0001, book-0001 이런식으로 진행
        }
    }

//    같은 상품인지 비교 method
    public boolean isSameProductCode(ProductCode other) {
        return this.value.substring(0, 4)
                .equals(other.value.substring(0, 4));
    }

}
