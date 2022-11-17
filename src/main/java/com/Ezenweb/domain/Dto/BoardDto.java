package com.Ezenweb.domain.Dto;

import com.Ezenweb.domain.entity.BoardEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// 롬북 : 생성자,get/set,toString,빌더패턴
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class BoardDto {
    // 1. 필드
    private int bno;             // 게시물번호
    private String btitle;       // 게시물제목
    private String bcontent;  // 게시물 내용
    private int bview;          // 조회수
    private String bfile;        // 첨부파일
    private  int mno;           // 작성자[회원번호 - fk]
    private int cno;             // 카테고리 [ 카테고리-fk ]

    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .bno(this.bno)
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .bfile(this.bfile)
                .mno(this.mno)
                .cno(this.cno)
                .build();
    }


}
