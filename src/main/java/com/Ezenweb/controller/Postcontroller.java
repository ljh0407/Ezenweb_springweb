package com.Ezenweb.controller;

import com.Ezenweb.domain.Dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController  // 컴포넌트 해당 클래스가 RestController 임을 빈에 등록
@RequestMapping("/api/v1/post-api") // 공통url  클래스로 들어오는 주소
public class Postcontroller {

            // 1. p.68
            @RequestMapping(value = "/domain" , method = RequestMethod.POST)
            public  String postExample(){return  "Hello Post API";}

            // 2. p69
            @PostMapping("/member")
            public String postMember(@RequestBody Map<String , String> postData  ){     // @RequestBody 쓰면 JSON과 통신가능
                return  postData.toString();
            }
            
            // 3. p.69
            @PostMapping("/member2")
            public  String postMemberDto(@RequestBody MemberDto memberDto){
                return  memberDto.toString();
            }
            
            
            
}
