package com.example.firstproject.controller;

import org.springframework.stereotype.Controller; //@Controller 를 하면 자동 import
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; //@GetMapping 을 하면 자동 import

//뷰 템플릿 : 웹 페이지를 일종의 틀로 만든 것이다. - mustache 파일이 해당되는 내용임

@Controller
//컨트롤러 : 클라이언트의 요청을 받아 서버에서 이를 처리하는 역할을 한다.

public class FirstController {

    @GetMapping("/hi") //클라이언트의 URL요청을 받아 특정 컨트롤러의 메소드가 처리하게 한다.
    public String niceToMeetYou(Model model) { //Model 객체(자동 임포트)
        //모델 : 뷰 템플릿에서 사용되는 데이터를 관리하는 역할을 한다.
        model.addAttribute("username", "jung");
        return "greetings"; //greeting.mustache 페이지를 반환하려면 파일이름만 적어주면 됨
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model){
        model.addAttribute("nickname", "jung");
        return "goodbye";
    }

}
