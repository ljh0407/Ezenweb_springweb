package com.Ezenweb.service;

import com.Ezenweb.domain.Dto.MemberDto;
import com.Ezenweb.domain.entity.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional // 트랜잭션  수정/삭제
@Service    // 해당 클래스가 Service임을 명시
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    public boolean setmember(MemberDto memberDto){
        memberRepository.save(memberDto.toEntity());
        return true;

    }
}
