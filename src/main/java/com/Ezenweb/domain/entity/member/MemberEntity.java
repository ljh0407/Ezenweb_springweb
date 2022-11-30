package com.Ezenweb.domain.entity.member;
import com.Ezenweb.domain.Dto.MemberDto;
import com.Ezenweb.domain.entity.BaseEntity;
import com.Ezenweb.domain.entity.board.BoardEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // 해당연결된 DB의 테이블과 매핑[연결]
@Table(name="member") // db에서 사용될 테이블 이름
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class MemberEntity extends BaseEntity {
    // 1. 필드
    @Id // 엔티티당 무조건 1개이상[ PK ]
    @GeneratedValue( strategy = GenerationType.IDENTITY ) // 자동번호 부여
    private int mno; // 회원번호 필드
    @Column(nullable = false) // db = not null
    private String memail; // 회원이메일 = 아이디
    @Column(nullable = false)  // db = not null
    private String mpassword; // 비밀번호 필드
    @Column(nullable = false)  // db = not null
    private String mphone;      // 전화번호 필드
    // 연관관계
    @OneToMany(mappedBy = "memberEntity") // [ 1: n ]일때 PK에 해당 어노테이션  mappedBy = "fk필드명"
    @Builder.Default // 빌더 사용시 해당 필드의 초기값설정
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    @Column// 회원등급
    private  String mrol;


    // 2. 생성자 [ 롬복으로 사용 ]
    // 3. 메소드 [ 롬복으로 사용 ]
    // * 엔티티 --> Dto
    public MemberDto toDto() { //형변환
        return  MemberDto
                .builder()  // 빌더 메소드 시작
                .mno(this.mno)
                .memail(this.memail)
                .mpassword(this.mpassword)
                .mphone(this.mphone)
                .build() ;   // 빌드 메소드 끝
    }
}
