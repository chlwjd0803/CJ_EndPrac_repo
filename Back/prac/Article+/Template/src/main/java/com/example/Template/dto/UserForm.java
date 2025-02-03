package com.example.Template.dto;

import com.example.Template.entity.User;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class UserForm {
    Long id;
    String username;
    String password;

    public User toEntity(){return new User(id,username,password);}

}
