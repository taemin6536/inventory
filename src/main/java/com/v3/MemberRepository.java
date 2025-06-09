package com.v3;

import org.jspecify.annotations.Nullable;

public interface MemberRepository {

    // 사용자를 찾지 못하면 null을 반환할 수 있음을 명시합니다.
    @Nullable User findById(String userId);

}
