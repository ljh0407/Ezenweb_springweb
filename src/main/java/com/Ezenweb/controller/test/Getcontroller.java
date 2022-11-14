package com.Ezenweb.controller.test;

import com.Ezenweb.domain.Dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api") // 요청 url 정의하기
public class Getcontroller {
    // 해당 클래스 접근[호출/요청] : http://localhost:8080/api/v1/get-api
        // 해당 클래스내 메소드 호출 : http://localhost:8080/api/v1/get-api/hello

    // 1. p, 57
    @RequestMapping( value = "/hello" , method = RequestMethod.GET)  // get 요청
    public  String gethello(){
        return  "해당 메소드로 들어옴"; // response 응답
    }

        // @RequestMapping
            // 1. @GetMapping
                //  @RequestMapping( value = "/hello" , method = RequestMethod.GET)
                //  @GetMapping("/hello")
            // 2. PostMapping
            // 3. PutMapping
            // 4. DeleteMapping
    // 2. p. 58
    @GetMapping("/name")
    public  String getName(){
        return  "Flature";
    }

    // 3. p. 59
    // 주의 : 경로상의 변수명[ {variable} ] 과 @PathVariable 매개변수 [ variable ] 동일하게 맞춘다
    @GetMapping("/variable1/{variable}")    // 경로상의 변수[값]  게시물 상세보기일때 사용하면 좋음
    public  String getVariable1(@PathVariable String variable ){
        return  variable;
    }

    // 4 - 1 . p. 60
    @GetMapping("/variable2/{variable}")
    public  String getVariable2( @PathVariable( value = "variable") String test ){
        return  test;
    }

    //  [변수1개] @PathVariable
    //  VS
    // [변수 여러개 키: 값]  @RequestParam
    //@PathVariable vs @RequestParam
    // 4- 2 , 4 - 1 비교
    @GetMapping("/variable3")
    public String getVariable3(@RequestParam String variable){
        return variable;
    }

    // 5. p. 61
    @GetMapping("request1") // URL ? 변수명=값1 & 변수명2 = 값2 & 변수명3 = 값3  (띄어쓰기금지)
    public  String getRequestParam1( @RequestParam String name , @RequestParam String email, @RequestParam String organization){
        return name + "" + email + "" + organization;
    }

    // 6. p. 62
    @GetMapping(value = "/request2")
    public  String getRequestParam2(@RequestParam Map<String , String> param){
        return  param.toString();
    }

    /*
        java 컬렉션 프레임워크
            1. list : 인덱스[중복가능] , set : 인덱스x[중복불가능] , map : 인덱스x[ 키 : 값 ] 엔트리 사용

        js
            1. JSON
     */

    // 7. p. 66
    @GetMapping("/request3")
    public  String getRequestParam3(MemberDto memberDto){
        return  memberDto.toString();

    }
}
