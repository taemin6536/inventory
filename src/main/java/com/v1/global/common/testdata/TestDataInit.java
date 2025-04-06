package com.v1.global.common.testdata;

import com.v1.product.domain.entity.Category;
import com.v1.product.domain.model.Product;
import com.v1.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("local") // local 환경에서만 동작
public class TestDataInit implements ApplicationRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // 테스트 데이터 생성
        Product product1 = Product.builder()
                .name("애플 맥북 프로 16인치")
                .description("최신 M2 프로 칩셋 탑재된 프리미엄 노트북")
                .category(Category.ELECTRONICS)
                .price(3600000)
                .build();

        Product product2 = Product.builder()
                .name("나이키 에어맥스")
                .description("편안한 착화감의 클래식 런닝화")
                .category(Category.FASHION)
                .price(129000)
                .build();

        Product product3 = Product.builder()
                .name("캣타워")
                .description("고양이를 위한 프리미엄 캣타워")
                .category(Category.PET)
                .price(89000)
                .build();

        // 데이터 저장
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

    }
}
