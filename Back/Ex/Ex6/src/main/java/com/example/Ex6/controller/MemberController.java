package com.example.Ex6.controller;

import com.example.Ex6.dto.MemberForm;
import com.example.Ex6.entity.Member;
import com.example.Ex6.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
@Slf4j
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/members/new")
    public String newMemberForm(){
        return "members/new";
    }

    @PostMapping("/members/join")
    public String joinMember(MemberForm form){
        //0. DTO객체 가져오기, 확인
        log.info(form.toString());

        //1. DTO를 엔티티로 변환
        Member member = form.toEntity();
        log.info(member.toString());

        //2. 엔티티를 DB에 저장
        Member saved = memberRepository.save(member); //엔티티를 매개변수
        log.info(saved.toString());

        return "redirect:/members/" + saved.getId(); //리다이렉트 정의 부분
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model md){
        Member memberEntity = memberRepository.findById(id).orElse(null);
        md.addAttribute("member", memberEntity);
        return "members/show";
    }

    @GetMapping("/members") //목록을 메인 페이지로 바꾼것
    public String index(Model md){
        ArrayList<Member> memberEntityList = memberRepository.findAll();
        md.addAttribute("memberList", memberEntityList);
        return "members/index";
    }
}
