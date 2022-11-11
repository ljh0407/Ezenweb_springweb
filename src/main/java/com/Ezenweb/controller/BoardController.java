package com.Ezenweb.controller;

import com.Ezenweb.domain.Dto.BoardDto;
import com.Ezenweb.service.Boardservice;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController // 해당 클래스가 컨트롤 목적으로 사용 // 스프링 MVC 관리 지향
@RequestMapping("/board")
public class BoardController {
    
    // 1. 게시물 목록 페이지 열기 [ HTML ] 열기
    @GetMapping("/list")     // URL 정의
    public Resource list(){
        return new ClassPathResource("templates/board/list.html");
    }
    // 2. 게시물 쓰기 페이지 열기
    @GetMapping("/write")
    public  Resource write(){
        return  new ClassPathResource("templates/board/write.html");
    }
// ------------------------------------------------------------
    // ----------------기능처리--------------
    // 1. 게시물 쓰기 처리 [ 첨부파일 ]
    @PostMapping("/setboard")
    public boolean setboard(@RequestBody BoardDto boarddto){

        // 1. dto 내용 확인
        System.out.println(boarddto.toString());
        // 2. -----> 서비스[ 비즈니스 로직 ] 으로 이동
        boolean result = new Boardservice().setboard(boarddto);
        // 3. 반환
        return true;
        // boolean :            ContentType : application/json
        // string :             ContentType : text/html; charset=  UTF-8
        // Resource :       ContentType : text/html

    }
    // 2. 게시물 목록보기 처리
    @GetMapping("/getboards")
    @ResponseBody
    public ArrayList<BoardDto> getboards(){
        // 1. -------------> 서비스[ 비즈니스 로직 ] 로 이동
        ArrayList<BoardDto> list = new Boardservice().getboards();
        // 2. 반환
        return  list;
    }
  /*  // 3. 게시물 개별조회 처리
    @GetMapping("/getboard")
    // 4. 게시물 수정 처리
    @PutMapping("/updateboard")
    // 5. 게시물 삭제 처리
    @DeleteMapping ("/deleteboard")*/

}
