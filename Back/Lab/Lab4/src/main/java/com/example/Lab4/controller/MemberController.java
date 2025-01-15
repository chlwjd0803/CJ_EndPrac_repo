package com.example.Lab4.controller;

import com.example.Lab4.dto.MemberForm;
import com.example.Lab4.entity.Member;
import com.example.Lab4.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/new")
    public String newMemberForm(){
        return "new";
    }

    @PostMapping("/join")
    public String joinMember(MemberForm form){
        //0. DTO객체 가져오기, 확인
        log.info(form.toString());
        //1. DTO를 엔티티로 변환
        Member member = form.toEntity();
        log.info(member.toString());
        //2. 엔티티를 DB에 저장
        Member saved = memberRepository.save(member); //엔티티를 매개변수
        log.info(saved.toString());
        return "";
    }
}
