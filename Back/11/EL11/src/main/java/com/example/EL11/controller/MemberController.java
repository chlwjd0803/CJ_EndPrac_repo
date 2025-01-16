package com.example.EL11.controller;

import com.example.EL11.dto.MemberForm;
import com.example.EL11.entity.Member;
import com.example.EL11.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model md){
        Member memberEntity = memberRepository.findById(id).orElse(null);
        md.addAttribute("member", memberEntity);
        return "members/edit";
    }

    @PostMapping("/members/change")
    public String change(MemberForm form){
        log.info(form.toString());
        Member memberEntity = form.toEntity();
        log.info(memberEntity.toString());
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        if(target != null){
            memberRepository.save(memberEntity);
        }
        return "redirect:/members/" + memberEntity.getId();
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("유저 삭제 요청이 들어왔습니다.");
        Member selectUser = memberRepository.findById(id).orElse(null);
        if(selectUser != null){
            log.info("삭제할 유저 정보 : " + selectUser.toString());
            memberRepository.delete(selectUser);
            rttr.addFlashAttribute("msg", "회원정보가 삭제되었습니다.");
        }
        else log.info("삭제할 유저 대상이 잘못되었습니다.");
        return "redirect:/members";
    }
}
