package com.Ezenweb.service;

import com.Ezenweb.domain.Dto.BoardDto;
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

        // 1. 로그인 정보 확인[ 세션 = loginMno ]
        Object object = request.getSession().getAttribute("loginMno" );
        if( object == null ){return false;}

        // 2. 로그인된 회원정보 호출
        int mno = (Integer)object;
        // 3. 회원정보 --> 회원정보 호출
        Optional<MemberEntity> optional = memberRepository.findById(mno);
        if(!optional.isPresent()){return false;}
        // 4. 로그인된 회원의 엔티티
        MemberEntity memberEntity = optional.get();
// 1. dto --> entity [ insert ] 저장된 entity 반환
        BoardEntity boardEntity = boardRepository.save(boardDto.toEntity() );
        // 2. 게시물번호가 0이 아니면
        if(boardEntity.getBno() != 0){
            // **** 5. fk 대입
            boardEntity.setMemberEntity(memberEntity);
            // *** 양방향 [ pk필드에 fk 연결 ]
            memberEntity.getBoardEntityList().add(boardEntity);
            return true;
        }
        else{return false;}
    }
    // 2. 게시물 목록 조회
    @Transactional
    public List<BoardDto> boardlist(){
        // 1. 모든 엔티티 호출한다.
       List<BoardEntity> elist =   boardRepository.findAll() ;
        // 2. 컨트롤에게 전달할때 형변환
        List<BoardDto> dlist = new ArrayList<>();
        // 3. 변환
        for(BoardEntity entity : elist){
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
    // 6. 조회수증가


}
