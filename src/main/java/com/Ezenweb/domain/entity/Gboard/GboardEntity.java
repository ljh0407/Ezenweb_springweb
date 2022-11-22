package com.Ezenweb.domain.entity.Gboard;

import com.Ezenweb.domain.Dto.GboardDto;
import com.Ezenweb.domain.entity.BaseEntity;
import com.Ezenweb.domain.entity.Gbcategory.GbcategoryEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "gboard")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class GboardEntity extends BaseEntity {
    @Id  // PK
    @GeneratedValue( strategy = GenerationType.IDENTITY) // 자동번호 부여
    private int gbno;

    @Column(nullable = false)
    private String gbtitle;

   @Column(nullable = false) // not null
    private String gbcontent;

   @Column(nullable = false) // not null
    private String gbwrite;


    // 연관관계
    @ManyToOne
    @JoinColumn(name = "gbcno")
    @ToString.Exclude
    private GbcategoryEntity gbcategoryEntity;

    // 형변환
    public GboardDto toDto() {
        return GboardDto
                .builder()
                .gbno(this.gbno)
                .gbtitle(this.gbtitle)
                .gbcontent(this.gbcontent)
                .gbwrite(this.gbwrite)
                .build();
    }

}
