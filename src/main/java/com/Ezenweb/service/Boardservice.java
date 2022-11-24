package com.Ezenweb.service;

import com.Ezenweb.domain.Dto.BcategoryDto;
import com.Ezenweb.domain.Dto.BoardDto;
import com.Ezenweb.domain.Dto.GbcategoryDto;
import com.Ezenweb.domain.Dto.GboardDto;
import com.Ezenweb.domain.entity.Gbcategory.GbcategoryEntity;
import com.Ezenweb.domain.entity.Gbcategory.GbcategoryRepository;
import com.Ezenweb.domain.entity.Gboard.GboardEntity;
import com.Ezenweb.domain.entity.Gboard.GboardRepository;
import com.Ezenweb.domain.entity.bcategory.BcategoryEntity;
import com.Ezenweb.domain.entity.bcategory.BcategoryRepository;
import com.Ezenweb.domain.entity.board.BoardEntity;
import com.Ezenweb.domain.entity.board.BoardRepository;
import com.Ezenweb.domain.entity.member.MemberEntity;
import com.Ezenweb.domain.entity.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

@Service // 컴포넌트 [ Spring MVC ]
public class Boardservice {
    // 1. -------------- 전역변수 -----[ 현재 서비스객체 @Autowired ]---------
    @Autowired // null
    private BoardRepository boardRepository;
    // @Transactional : 엔티티 DML 적용 할때 사용되는 어노테이션
    @Autowired
    private HttpServletRequest request; // 요청 객체 선언
    @Autowired
    private HttpServletResponse response; // 응답 객체 선언
    @Autowired
    private MemberRepository memberRepository;  // 회원 리포지토리 객체 선언
    @Autowired
    private BcategoryRepository bcategoryRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private GboardRepository gboardRepository;
    @Autowired
    private GbcategoryRepository gbcategoryRepository;


    // 첨부파일 경로
    String path = "C:\\Users\\504\\Desktop\\springweb\\Ezenweb_springweb\\src\\main\\resources\\static\\js\\bupload\\";
        /*
                1. insert : boardRepository.save(엔티티)  BoardEntity entity
                2. select : boardRepository.findAll()           List<BoardEntity> elist
                3. select : boardRepository.findby_id(pk번호) Optional<BoardEntity> optional
                4. delete : boardRepository.delete ( 삭제할엔티티 )

         */

    // 2. -------------- 서비스 ------------
    // 0. 첨부파일 다운로드
    public void filedownload( String filename){
        String realfilename = ""; // uuid 제거
        String [] split = filename.split("_");  // 1. _기준으로 자르기
        for( int i = 0 ; i<split.length ; i++){ // 2. uuid 제외한 반복문 돌리기
            realfilename += split[i];       // 3. 뒷자리 문자열 추가
            if(split.length-1 != i ){       // 마지막 인덱스가 아니면
                realfilename += "_";       // 문자열[1] _ 문자열[2] _ 문자열 [3]. 확장자명
            }
        }
        String filepath = path+filename; // 1. 경로 찾기
        // 2. 헤더 구성 [
        try {
            response.setHeader( // 응답
                    "Content-Disposition", // 다운로드 형식 [ 브라우저 마다 다름 ]
                    "attachment; filename=" + URLEncoder.encode( realfilename, "UTF-8" ) );  // 다운로드에 표시될 파일명
            File file = new File( filepath );
            // 3. 다운로드 스트림 [ ]
            // 1. 다운로드 할 파일 바이트 읽어올 스트림 객체 선언
            BufferedInputStream fin = new BufferedInputStream( new FileInputStream(file) );
            // 2. 파일의 길이만큼 배열 선언
            byte[] bytes = new byte[ (int)file.length() ];
            // 3. 파일의 길이만큼 읽어와서 바이트를 배열에 저장
            fin.read(bytes);
            // 4. 출력 스트림 객체 선언
            BufferedOutputStream fout = new BufferedOutputStream(response.getOutputStream() );
            // 5. 응답하기 [ 배열 내보내기 ]
            fout.write(bytes);
            // 6. 버퍼 초기화 혹은 스트림 닫기
            fout.flush(); fout.close(); fin.close();
         }catch (Exception e){System.out.println(e);}
    }

    // ** 첨부파일 업로드
    @Transactional    //  boardDto : 쓰기,수정 대상     BoardEntity:원본
    public boolean fileupload( BoardDto boardDto  , BoardEntity boardEntity) {
        if (boardDto.getBfile() != null) {  // ** 첨부파일 있을때
            // * 업로드된 파일의 이름 [ 문제점 : 파일명 중복 ]
            String uuid = UUID.randomUUID().toString();  // 1. 난수생성
            String filename = uuid + "_" + boardDto.getBfile().getOriginalFilename(); // 2. 난수+파일명
            // * 첨부파일명 db에 등록
            boardEntity.setBfile(filename); // 해당 파일명 엔티티에 저장 // 3. 난수 + 파일명으 엔티티에 저장
            // * 첨부파일업로드 // 3. 저장할 경로 [ 전역변수 ]
            try {
                File upliardfile = new File(path + filename);   // 4. 경로 + 파일명 [ 객체화 ]
                boardDto.getBfile().transferTo(upliardfile);  // 5. 해당객체 경로로 업로드
            } catch (Exception e) {
                System.out.println("첨부파일 업로드 실패 ");
            }
            return true;
        }else {return false;}
    }
    // 1. 게시물 쓰기
    @Transactional
    public boolean setboard( BoardDto boardDto ){
        //-----------------------------------------//
        MemberEntity memberEntity = memberService.getEntity();
        if(memberEntity == null){return false;}
        // ---------------------------- //
        // 선택한 번호
        Optional<BcategoryEntity> optional = bcategoryRepository.findById(boardDto.getBcno() );
        if(!optional.isPresent() ){return  false;}
        BcategoryEntity bcategoryEntity = optional.get();
        // --------------------------  //
        // 1. dto --> entity [ insert ] 저장된 entity 반환
        BoardEntity boardEntity = boardRepository.save(boardDto.toEntity() );
        // 2. 게시물번호가 0이 아니면
        if( boardEntity.getBno() != 0 ){   // 2. 생성된 entity의 게시물번호가 0 이 아니면  성공

            fileupload( boardDto , boardEntity ); // 업로드 함수 실행

            // 1. 회원 <---> 게시물 연관관계 대입
            boardEntity.setMemberEntity( memberEntity ); // ***!!!! 5. fk 대입
            memberEntity.getBoardEntityList().add( boardEntity); // *** 양방향 [ pk필드에 fk 연결 ]
            // 2. 카테고리 <---> 게시물 연관관계 대입
            boardEntity.setBcategoryEntity( bcategoryEntity );
            bcategoryEntity.getBoardEntityList().add( boardEntity );
            return true;
        }
        else{ return false; } // 2. 0 이면 entity 생성 실패
    }


    // 2. 게시물 목록 조회
    @Transactional
    public List<BoardDto> boardlist( int bcno){
        List<BoardEntity> elist = null;
        if( bcno == 0 ){ // 카테고리번호가 0 이면 전체보기
          elist =  boardRepository.findAll() ;  // 1. 모든 엔티티 호출한다.
        } else {
         BcategoryEntity bcEntity =  bcategoryRepository.findById( bcno ).get();
         elist = bcEntity.getBoardEntityList(); // 해당 엔티티의 게시물목록
        }// 카테고리 번호가 0이 아니면 선택된 카테고리별 보기
            List<BoardDto> dlist = new ArrayList<>();  // 2. 컨트롤에게 전달할때 형변환
            for(BoardEntity entity : elist){  // 3. 변환
                dlist.add(entity.toDto());
            }
            return dlist;
    }
    // 3. 게시물 개별 조회
    @Transactional
    public BoardDto getboard( int bno){
        // 1. 입력받은 게시물번호로 엔티티 검색 [ Optional ]
        Optional<BoardEntity> optional = boardRepository.findById(bno);
        // 2. Optional 안에 있는 내용물 확인 .isPresent()
        if(optional.isPresent()){
            BoardEntity boardEntity = optional.get();
            return boardEntity.toDto();
        }else {
        return null;  // 4. 없으면 null
        }
    }
    // 4. 게시물삭제
    @Transactional
    public boolean delboard(int bno){
        // 1. 입력받은 게시물번호로 엔티티 검색 [ Optional ]
        Optional<BoardEntity> optional = boardRepository.findById(bno);
        // 2. Optional 안에 있는 내용물 확인.isPresent()
        if( optional.isPresent()){
            BoardEntity boardEntity = optional.get();
            if (boardEntity.getBfile() != null) { // 기존 첨부파일 있을때
                File file = new File(path + boardEntity.getBfile());
                if (file.exists()) {
                    file.delete();
                }
            }
            boardRepository.delete(boardEntity);
            return true;
        }else {return false;}
    }
    // 5. 게시물 수정 [ 첨부파일 1. 첨부파일이 있을때->첨부파일변경 2. 첨부파일이 없을때->첨부파일추가 ]
    @Transactional
    public boolean upboard(BoardDto boardDto){
        System.out.println("수정 : "+boardDto.toString());
        // 1. DTO 수정할 PK번호 이용해서 엔티티 찾기
        Optional<BoardEntity> optional = boardRepository.findById(boardDto.getBno() );
        if(optional.isPresent() ) {
            BoardEntity boardEntity = optional.get();
            // 1. 수정할 첨부파일이 있을때  ---> 새로운 첨부파일 업로드 , db 수정한다.
            if (boardDto.getBfile() != null) { // boardDto : 수정할 정보 boardEntity : 원본
                if (boardEntity.getBfile() != null) { // 기존 첨부파일 있을때
                    File file = new File(path + boardEntity.getBfile());    // 기존 첨부파일 객체화
                    if (file.exists()) {
                        file.delete();}
                }
                fileupload(boardDto, boardEntity);   // 업로드 함수 실행
            }
            // 수정처리 [ 메소드 별도 존재x / 엔티티<---> 레코드 / 엔티티 객체 자체를 수정 ]
            boardEntity.setBtitle(boardDto.getBtitle());
            boardEntity.setBcontent(boardDto.getBcontent());
            return true;
        }else{ return false;}
    }
    // 6. 카테고리 등록
    public  boolean setbcategory(BcategoryDto bcategoryDto){
        BcategoryEntity entity = bcategoryRepository.save(bcategoryDto.toEntity() );
        if( entity.getBcno() != 0 ){return true;}
        else {return false;}
    }
    // 7. 모든 카테고리 출력
    public List<BcategoryDto> bcategorylist(){
        List<BcategoryEntity> entityList = bcategoryRepository.findAll();
        List<BcategoryDto> dtolist = new ArrayList<>();
        entityList.forEach( e -> dtolist.add(e.toDto() ) );
        return dtolist;
    }
    @Transactional
    // 8. 비회원게시판 글등록
    public boolean setgboard(GboardDto gboardDto){
        System.out.println(gboardDto.getGbno());
       GboardEntity gboardEntity = gboardRepository.save( gboardDto.toEntity() );
       Optional<GbcategoryEntity> optional = gbcategoryRepository.findById(gboardDto.getGbcno() );
        System.out.println(optional.isPresent());
       if(!optional.isPresent() ){return false;}
       GbcategoryEntity gbcategoryEntity = optional.get();
       // dto --> entity [ insert ] 저장된 entity 반환
       if(gboardEntity.getGbno() != 0){
           gboardEntity.setGbcategoryEntity(gbcategoryEntity);
           gbcategoryEntity.getGboardEntityList().add(gboardEntity);
           return true;
       }else{
           System.out.println("test");
           return false;
       }

    }
    @Transactional
    // 9. 비회원게시물 목록
    public List<GboardDto> gboardlist(int gbcno){
        List<GboardEntity> gboardEntityList = null;
        if(gbcno == 0) {//카테고리 번호가 0 이면 전체보기
            gboardEntityList = gboardRepository.findAll();  // 모든 엔티티 호출
        }else {
            GbcategoryEntity gbcategoryEntity = gbcategoryRepository.findById(gbcno).get();
            System.out.println("gbcno :"+gbcno);
            gboardEntityList = gbcategoryEntity.getGboardEntityList(); // 선택한 엔티티 게시글 목록
        } // 선택된 카테고리별 보기
        List<GboardDto> gboardlist = new ArrayList<>(); //컨트롤에게 전달할떄 형변환
        for(GboardEntity gboardEntity : gboardEntityList){ // 변환
            gboardlist.add( gboardEntity.toDto());
        }
        return gboardlist;
    }
    @Transactional
    // 10. 비회원게시판 카테고리등록
    public boolean setgbcategory(GbcategoryDto gbcategoryDto){
        GbcategoryEntity entity = gbcategoryRepository.save( gbcategoryDto.toEntity());
        if(entity.getGbcno() != 0){
            return  true;
        }else{return false;}
    }
    @Transactional
    // 11. 비회원 카테고리 출력
    public List<GbcategoryDto> gbcategorylist(){
        List<GbcategoryEntity> gbcategoryEntityList = gbcategoryRepository.findAll();
        List<GbcategoryDto> gbcategorylist = new ArrayList<>();
        gbcategoryEntityList.forEach(g -> gbcategorylist.add(g.toDto()));
        return  gbcategorylist;
    }

    // 12. 비회원게시판 수정
    @Transactional
    public boolean upgboard(GboardDto gboardDto){
    // 수정할 번호 호출
    Optional<GboardEntity> optional = gboardRepository.findById(gboardDto.getGbno());
        if (optional.isPresent()) {
            GboardEntity gboardEntity = optional.get();
            // 수정처리
            gboardEntity.setGbtitle(gboardDto.getGbtitle());
            gboardEntity.setGbcontent(gboardDto.getGbcontent());
            return true;
            }else{
            return false;
             }
        }
}

/*
      // 1. MultipartFile 인터페이스
         // .getOriginalFilename() : 해당 인터페이스에 연결(주소)된 파일의 이름 호출
         // .transferTo() : 파일이동 [ 사용자pc ---> 개발자pc ]
         // .transferTo( 파일객체 )
         // File

           // 1. pk + 파일명
         // 2. uuid + 파일명
         // 3. 업로드 날짜/시간 + 파일명
         // 4. 중복된 파일명 중 최근파일명뒤에  파일명 + (중복수+1)
* */

/*

 // 1. 리스트를 순회하는 방법 3가지
         for(int i = 0 ;  i < entityList.size(); i++){
           BcategoryEntity e = entityList.get( i );
            System.out.println(e.toString() );
        }
        for( BcategoryEntity e : entityList){
            System.out.println(e.toString() );
        }
         entityList.forEach( e -> {} );
          // 화살표함수[람다식표현] js : (인수) => { 실행코드 } java : -> { 실행코드 }
 */