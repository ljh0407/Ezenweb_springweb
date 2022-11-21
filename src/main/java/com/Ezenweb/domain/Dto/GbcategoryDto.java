package com.Ezenweb.domain.Dto;

import com.Ezenweb.domain.entity.Gbcategory.GbcategoryEntity;
import lombok.*;

import javax.persistence.Entity;


@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString @Builder
public class GbcategoryDto {

    private int gbcno;
    private String gbcname;
    private int gbno;

    public GbcategoryEntity toEntity(){
        return GbcategoryEntity.builder()
                .gbcno(this.gbcno)
                .gbcname(this.gbcname)
                .gbcno(this.gbcno)
                .build();
    }
}
