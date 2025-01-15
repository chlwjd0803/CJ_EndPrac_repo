package com.example.Ex5.controller;

import com.example.Ex5.dto.MemberForm;
import com.example.Ex5.entity.Member;
import com.example.Ex5.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model md){
        log.info("id = " + id);
        // 1. id를 조회해 데이터 가져오기
        Member memberEntity = memberRepository.findById(id).orElse(null);
        //Optional<Member> memberEntity = memberRepository.findById(id); 도 가능

        // 2. 모델에 데이터 등록하기
        md.addAttribute("member", memberEntity);

        // 3. 뷰 페이지 반환하기
        return "show";
    }

    @GetMapping("members")
    public String index(Model md){
        // 1. 모든 데이터 가져오기
        ArrayList<Member> memberEntityList = memberRepository.findAll(); // 이 방식이 나음(레포 오버라이딩하기)
        // 다운캐스팅 방식
        // List<Member> memberEntityList = (List<Member>)memberRepository.findAll();
        // 업캐스팅 방식
        // Iterable<Member> memberEntityList = memberRepository.findAll();

        // 2. 모델에 데이터 등록하기
        md.addAttribute("memberList", memberEntityList);
        // 3. 뷰 페이지 설정하기
        return "index";
    }
}
