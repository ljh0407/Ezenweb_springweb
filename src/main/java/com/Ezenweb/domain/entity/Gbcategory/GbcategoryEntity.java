package com.Ezenweb.domain.entity.Gbcategory;

import com.Ezenweb.domain.Dto.GbcategoryDto;
import com.Ezenweb.domain.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
@Entity @Table(name = "gbcategory")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString @Builder
public class GbcategoryEntity extends BaseEntity {
    @Id // pk
    @GeneratedValue( strategy = GenerationType.IDENTITY) // 자동번호
    private int gbcno;
    @Column(nullable = false)
    private String gbcname;
    @Column(nullable = false)
    private int gbno;

    // 연관관계
    @OneToMany
    @JoinColumn(name = "gbno")



    // 형변환
    public GbcategoryDto toDto(){
        return GbcategoryDto.builder()
                .gbcno(this.gbcno)
                .gbcname(this.gbcname)
                .gbcno(this.gbcno)
                .build();
    }

}
