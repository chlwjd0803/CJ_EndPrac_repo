package com.example.Template.controller;

import com.example.Template.dto.UserForm;
import com.example.Template.entity.User;
import com.example.Template.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserRepository userRepository;

    /*
    @PostMapping("/users/register")
    public String signup(UserForm form){
        User user = form.toEntity();
        // 중복이 있는지 확인하는 조건문 추가예정
        userRepository.save(user);
        return "redirect:/";
    }

     */

    @PostMapping("/users/login")
    public String signin(UserForm form){
        return null;
    }

    /*
    @PatchMapping("/users/edit")
    public String edit(UserForm form){

    }
     */

}
