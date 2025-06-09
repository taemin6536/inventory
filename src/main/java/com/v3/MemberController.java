package com.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    public User findUser(
            @PathVariable final String id
    ){
        return memberService.findUser(id);
    }


}
