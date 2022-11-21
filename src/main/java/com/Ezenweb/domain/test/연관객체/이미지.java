package com.Ezenweb.domain.test.연관객체;

import lombok.ToString;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@ToString
public class 이미지 {
    String  이미지명;
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "제품명")
    제품 제품;  // 주인
}
