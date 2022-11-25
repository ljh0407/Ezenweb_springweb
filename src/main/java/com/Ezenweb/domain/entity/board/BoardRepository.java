package com.Ezenweb.domain.entity.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository< BoardEntity , Integer > { // Repository dao 역할

        // 1. 기본메소드 외 메소드 추가
            // 1. .findById( pk값 ) : 해당 pk의 엔티티 하나 호출
            // 2. .findByAll() : 모든 엔티티를 호출
            // 3. [ 직접 fine 만들기 ] :  .findBy필드명(조건)  [ Optional ]
            // 3.                                     .findBy필드명(조건) [ List<엔티티명> ]
        // 3.                                     .findBy필드명(조건 , Pageable pageable)     [ Page<엔티티명> ]

            // 1. @Query( value = "쿼리문작성" , nativeQuery = true)
                // sql [ 쿼리문 ] 변수 넣기
                //  [ 인수 ] @Param("변수명") 자료형 변수명 ------->   : 변수명       vs        ?인수순서번호
                //  [ 인수 ] ( 자료형 변수명 , 자료형 변수명 , 자료형 변수명 ) ------> ? 인수번호
                    // @Param("변수명")  생략가능 [ jdk 8 버전 이상 ]



   /* @Query( value = "select * from board  where bcno = :bcno" ,nativeQuery = true)
    Page<BoardEntity> findBybcno( int bcno , Pageable pageable);*/
                // Page 사용하는 이유 페이징처리 할려고
                                     //  select p(board) from board p where p.bcno = ?1
        /*@Query( value = "select p from board p where p.bcno = ?1" ,nativeQuery = true)
        Page<BoardEntity> findBybcno(@Param("bcno") int bcno , Pageable pageable);*/

        // 1. 제목 검색
        @Query(value = "select * from board where bcno = :bcno and btitle like %:keyword%" , nativeQuery = true)
        Page<BoardEntity> findBybtitle(int bcno , String keyword , Pageable pageable);
        // 2. 내용 검색
        @Query(value = "select * from board where bcno = :bcno and bcontent like %:keyword%:" , nativeQuery = true)
        Page<BoardEntity> findBybcontent( int bcno , String keyword , Pageable pageable);
        // 3. 검색이 없을때
        @Query( value = "select * from board  where bcno = :bcno" ,nativeQuery = true)
        Page<BoardEntity> findBybcno(@Param("bcno") int bcno , Pageable pageable);
}
