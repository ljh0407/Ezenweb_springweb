package com.Ezenweb.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity   //  해당연결된 DB의 테이블과 매핑 [ 연결 ]
@Table(name = "member")  //  db에서 사용될 테이블 이름
@NoArgsConstructor @AllArgsConstructor @Getter
@Setter @ToString @Builder
public class MemberEntity {

    // 1. 필드
    @Id  // 엔티티당 무조건 1개이상 [ PK ]
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동번호 부여 = sql : auto_increment 과 같음
    private int mno;
    private String memail;
    private String mpassword;
    // 2. 생성자 [ 롬북으로 사용 ]
    // 3. 메소드 [ 롬북으로 사용 ]
}