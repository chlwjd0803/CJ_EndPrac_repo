package com.example.Lab3.controller;


import com.example.Lab3.dto.MemberForm;
import com.example.Lab3.entity.Member;
import com.example.Lab3.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/new")
    public String newMemberForm(){
        return "new";
    }

    @PostMapping("/join")
    public String joinMember(MemberForm form){
        System.out.println(form.toString());
        //1. DTO를 엔티티로 변환
        Member member = form.toEntity();
        System.out.println(member.toString());
        //2. 엔티티를 DB에 저장
        Member saved = memberRepository.save(member); //엔티티를 매개변수
        System.out.println(saved.toString());
        return "";
    }

}
