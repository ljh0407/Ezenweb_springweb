package com.Ezenweb.domain.Dto;

import com.Ezenweb.domain.entity.Gboard.GboardEntity;
import lombok.*;

import javax.persistence.Id;
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class GboardDto {
    private int gbno;
    private String gbtitle;
    private String gbcontent;
    private String gbwrite;
    private int gbcno;

    // 형변환
    public GboardEntity toEntity(){
        return GboardEntity
                .builder()
                .gbno(this.gbno)
                .gbtitle(this.gbtitle)
                .gbcontent(this.gbcontent)
                .gbwrite(this.gbwrite)
                .gbcno(this.gbcno)
                .build();
    }

}
