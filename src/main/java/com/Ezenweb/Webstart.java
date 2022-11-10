package com.Ezenweb;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // 스프링 웹 기본설정 [ 1. MVC 2. 내장서버 3. RESTFUL 4. 웹설정 ]

public class Webstart {

    public static void main(String[] args) {
                                // main 스레드
        SpringApplication.run(Webstart.class);  // 스프링 어플리케이션 실행 [ 현재클래스명 .class]


    }
}



/*
        1. extend : 상속 [ 설계도 물려받는다. 1개만 가능 ]
        2. @ 어노테이션 [ 여러개 가능 / 빌드[실행] 할때 자동적으로 코드 실행 ]
            1. 내장 : @override : 상속 메소드 재정의
            
            2. 메타 :
 */