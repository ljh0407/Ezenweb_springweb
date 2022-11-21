package com.Ezenweb.domain.entity.bcategory;

import com.Ezenweb.domain.Dto.BcategoryDto;
import com.Ezenweb.domain.entity.BaseEntity;
import com.Ezenweb.domain.entity.board.BoardEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "bcategory")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder @ToString
public class BcategoryEntity extends BaseEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY) // 자동번호 부여
    private int bcno;  // 카테고리 번호
    private String bcname;  // 카테고리이름

    @OneToMany( mappedBy = "bcategoryEntity")
    @Builder.Default
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    public BcategoryDto toDto() {
        return BcategoryDto
                .builder()
                .bcno(this.bcno)
                .bcname(this.bcname)
                .build();
        // this : 해당 메소드를 호출하는 객체의 필드 호출시 사용
    }
}
