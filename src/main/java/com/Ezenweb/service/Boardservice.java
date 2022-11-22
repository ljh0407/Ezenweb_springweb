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
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service // 컴포넌트 [ Spring MVC ]
public class Boardservice {
    // 1. -------------- 전역변수 --------------
    @Autowired // null
    private BoardRepository boardRepository;
    // @Transactional : 엔티티 DML 적용 할때 사용되는 어노테이션
    @Autowired
    private HttpServletRequest request; // 요청 객체 선언
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
        /*
                1. insert : boardRepository.save(엔티티)
                2. select : boardRepository.findAll()
                3. select : boardRepository.findby_id(pk번호)
                4. delete : boardRepository.delete

         */

    // 2. -------------- 서비스 ------------
    // 1. 게시물 쓰기
    @Transactional
    public boolean setboard( BoardDto boardDto){
        //-----------------------------------------//
        MemberEntity memberEntity = memberService.getEntity();
        if(memberEntity == null){return false;}
        // 선택한 번호
        Optional<BcategoryEntity> optional = bcategoryRepository.findById(boardDto.getBcno() );
        if(!optional.isPresent() ){return  false;}
        BcategoryEntity bcategoryEntity = optional.get();
        // 1. dto --> entity [ insert ] 저장된 entity 반환
        BoardEntity boardEntity = boardRepository.save(boardDto.toEntity() );
        // 2. 게시물번호가 0이 아니면
        if(boardEntity.getBno() != 0){
            // **** 5. fk 대입
            // 1. 회원 <---> 게시물 연관관계 대입
            boardEntity.setMemberEntity(memberEntity);  // p. 268 32번 줄
            // *** 양방향 [ pk필드에 fk 연결 ]
            memberEntity.getBoardEntityList().add(boardEntity); // 43번 줄
            // 2. 카테고리<---> 게시물 연관관계 대입
            boardEntity.setBcategoryEntity(bcategoryEntity);
            bcategoryEntity.getBoardEntityList().add(boardEntity);
          
            return true;
        }
        else{return false;}
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
            BoardEntity entity = optional.get();
            boardRepository.delete(entity);
            return true;
        }else {return false;}
    }
    // 5. 게시물 수정
    @Transactional
    public boolean upboard(BoardDto boardDto){
        // 1. DTO 수정할 PK번호 이용해서 엔티티 찾기
        Optional<BoardEntity> optional = boardRepository.findById(boardDto.getBno() );
        if(optional.isPresent() ){
            BoardEntity entity = optional.get();
            // 수정처리 [ 메소드 별도 존재x / 엔티티<---> 레코드 / 엔티티 객체 자체를 수정 ]
            entity.setBtitle(boardDto.getBtitle());
            entity.setBcontent(boardDto.getBcontent());
            entity.setBfile(boardDto.getBfile());
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
       GboardEntity entity = gboardRepository.save( gboardDto.toEntity() );
       if(entity.getGbno() != 0){
           return true;
       }else {return false;}
    }

    // 9. 비회원게시물 목록
    public List<GboardDto> gboardlist(){
        List<GboardEntity> gboardEntityList = gboardRepository.findAll();
        List<GboardDto> gboardDtoList = new ArrayList<>();
        gboardEntityList.forEach( g -> gboardDtoList.add(g.toDto()) );
        return gboardDtoList;
    }

    // 10. 비회원게시판 카테고리등록
    public boolean setgbcategory(GbcategoryDto gbcategoryDto){
        GbcategoryEntity entity = gbcategoryRepository.save( gbcategoryDto.toEntity());
        if(entity.getGbcno() != 0){
            return  true;
        }else{return false;}
    }

    // 11. 비회원 카테고리 출력
    public List<GbcategoryDto> gbcategorylist(){
        List<GbcategoryEntity> gbcategoryEntityList = gbcategoryRepository.findAll();
        List<GbcategoryDto> gbcategorylist = new ArrayList<>();
        gbcategoryEntityList.forEach(gbc -> gbcategorylist.add(gbc.toDto()));
        return  gbcategorylist;
    }
}



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