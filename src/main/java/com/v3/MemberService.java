package com.v3;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

//클래스에 달아도됨
//@NullMarked
@Service
@RequiredArgsConstructor
// @NullMarked 덕분에 아래의 모든 타입은 @NonNull로 간주됩니다.
public class MemberService {
    private final MemberRepository memberRepository;

    // 이 메소드는 User 를 반환하거나, 못찾으면 명시적으로 null 을 반환함
    public @Nullable User findUser(String id) {
        return memberRepository.findById(id);
    }

    // User 와 id는 절대 null 이 될 수 없음 (기본값)
    // 하지만 name 은 null 일 수 있음을 명시
    public void updateUser(User user, @Nullable String name) {
        user.changeName(name);
    }



    // name 파라미터는 절대 null이 될 수 없습니다.
    // 반환되는 String 또한 절대 null이 될 수 없습니다.
    public String getUserGreeting(String name) {
        return "Hello, " + name.toUpperCase();
    }
}
