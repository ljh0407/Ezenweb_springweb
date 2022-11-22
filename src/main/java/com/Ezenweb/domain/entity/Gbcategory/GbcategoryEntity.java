package com.Ezenweb.domain.entity.Gbcategory;

import com.Ezenweb.domain.Dto.GbcategoryDto;
import com.Ezenweb.domain.entity.BaseEntity;
import com.Ezenweb.domain.entity.Gboard.GboardEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "gbcategory")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString @Builder
public class GbcategoryEntity extends BaseEntity {
    @Id // pk
    @GeneratedValue( strategy = GenerationType.IDENTITY) // 자동번호
    private int gbcno;

    @Column(nullable = false)
    private String gbcname;



    // 연관관계
    @OneToMany(mappedBy ="gbno")
    @Builder.Default
    @ToString.Exclude
    private List<GboardEntity> gboardEntityList = new ArrayList<>();




    // 형변환
    public GbcategoryDto toDto(){
        return GbcategoryDto.builder()
                .gbcno(this.gbcno)
                .gbcname(this.gbcname)
                .build();
    }

}
