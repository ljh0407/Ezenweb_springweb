package com.Ezenweb.controller;

import com.Ezenweb.domain.Dto.MemberDto;
import com.Ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // 해당 클래스가 RestController 임을 명시
public class MemberController {
    @Autowired  // 제어역전 [ DI ] 스프링 컨네이너 위임
    private MemberService memberService;
    @GetMapping("/setmember")
    public boolean setmember(){
        MemberDto memberDto = new MemberDto( 0 , "qwe@qwe.com"  , "qwe");
        boolean result = memberService.setmember(memberDto);
                return result; // 3. 응답
    }
}
