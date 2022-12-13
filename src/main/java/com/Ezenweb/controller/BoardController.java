package com.Ezenweb.controller;

import com.Ezenweb.domain.Dto.*;
import com.Ezenweb.service.Boardservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController // @Controller + @ResponseBody
@RequestMapping("/board") // 공통 URL
public class BoardController {
    // 컨트롤 역할 : HTTP 요청 / Model and View 응답

    // 1. -------------- 전역변수 --------------
    // 1. 서비스 메소드 호출 위한 객체 생성 [ ioc 제어역전 ]
        // 1. 개발자가 new 사용해서 jvm힙 메모리 할당해서 객체 생성
        // private Boardservice boardservice = new Boardservice();
        // 2. @Autowired 어느테이션 이용해서 Spring 컨테이너에 빈[메모리] 생성
    @Autowired
    private Boardservice boardservice;
 /*   // 2. -------------- 페이지 로드 [view] -------------
        // 1. 게시물목록 페이지 열기
        @GetMapping("/list")
        public Resource getlist(){
            return new ClassPathResource("templates/board/list.html");
        }
        // 2. 게시물쓰기 페이지 열기
        @GetMapping("/write")
        public Resource getwrite(){return new ClassPathResource("templates/board/write.html");}

    // 3. 게시물조회 페이지 열기
        @GetMapping("/view")
        public Resource getview(){return new ClassPathResource("templates/board/view.html");}

    // 4. 게시물수정 페이지 열기
        @GetMapping("/update")
        public Resource getupdate(){return new ClassPathResource("templates/board/update.html");}

    // 5. 비회원게시판 페이지 열기
        @GetMapping("/gboard")
        public Resource gboard(){return new ClassPathResource("templates/board/gboard.html");}*/
    // 3. -------------- 요청과응답 처리 [ model] -----------------
        // 1. HTTP 요청 메소드 매핑 : @PostMapping @GetMapping @DeleteMapping @PutMapping
        // 2. HTTP 데이터 요청 메소드 매핑 :  @RequestBody @RequestParam
    /*
    @PostMapping("/setboard")  첨부파일 없을때
    public boolean setboard(@RequestBody BoardDto boardDto){
        System.out.println("확인 : "+ boardDto.toString() );
        return boardservice.setboard(boardDto);
    }    */
        // 1. 게시물 쓰기 [ 첨부파일 있을때 @RequestBody 생략 ]
        @PostMapping("/setboard")
        public boolean setboard( BoardDto boardDto){
            System.out.println("확인 : "+ boardDto.toString() );
            return boardservice.setboard(boardDto);
        }
        // 2. 게시물 목록 조회 [ 페이징,검색 ]
        @PostMapping("/boardlist")
        public PageDto boardlist(@RequestBody PageDto pageDto) {
            return boardservice.boardlist(pageDto);
        }
        // 3. 게시물 개별 조회
        @GetMapping("/getboard")
        public BoardDto getboard(@RequestParam("bno") int bno){
            return boardservice.getboard(bno);

        }
        // 4. 게시물 삭제
        @DeleteMapping("/delboard")
        public boolean delboard(@RequestParam("bno") int bno){
            return boardservice.delboard(bno);

        }
        // 5. 게시물 수정 [ 첨부파일 ]
        @PutMapping("/upboard")
        public boolean upboard( BoardDto boardDto){
            return boardservice.upboard(boardDto);
        }
        // 6. 카테고리 등록
        @PostMapping("/setbcategory")
        public  boolean setbcategory(@RequestBody BcategoryDto bcategoryDto){
            return boardservice.setbcategory(bcategoryDto);
        }
        // 7. 모든 카테고리 출력
        @GetMapping("/bcategorylist")
        public List<BcategoryDto> bcategoryList(){
            return  boardservice.bcategorylist();
        }

        // 8. 첨부파일 다운로드
        @GetMapping("/filedownload")
        public void filedownload(@RequestParam("filename") String filename){
            boardservice.filedownload(filename);
        }

        // 8. 비회원게시판 글등록
        @PostMapping("/setgboard")
        public  boolean setgboard(@RequestBody GboardDto gboardDto){
            System.out.println(gboardDto.toString());
            return boardservice.setgboard(gboardDto);
        }

        // 9. 비회원게시판 글목록
        @GetMapping("/gboardlist")
        public List<GboardDto> gboardlist(@RequestParam("gbcno") int gbcno){
            System.out.println("리스트 : "+gbcno);
            return boardservice.gboardlist( gbcno);
        }

        // 10. 카테고리 등록
        @PostMapping("/setgbcategory")
        public  boolean setgbcategory(@RequestBody GbcategoryDto gbcategoryDto){
            return boardservice.setgbcategory(gbcategoryDto);
        }

        // 11. 카테고리 출력
        @GetMapping("/gbcategorylist")
        public List<GbcategoryDto> gbcategorylist(){
            return boardservice.gbcategorylist();
        }

        // 비회원글 수정
        @PutMapping("/upgboard")
        public boolean upgboard(@RequestBody GboardDto gboardDto){
        return boardservice.upgboard(gboardDto);
        }

}
