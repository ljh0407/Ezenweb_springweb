package com.Ezenweb.domain.Dto;

import lombok.*;
// 롬북 : 생성자,get/set,toString,빌더패턴
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class BoardDto {
    private  String btitle;
    private  String bcontent;

}
