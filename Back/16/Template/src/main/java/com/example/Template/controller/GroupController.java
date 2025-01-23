package com.example.Template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// 의미 없는 문제
//@Controller
//public class GroupController {
//    @Autowired
//    private GroupService groupService;
//    @Autowired
//    private TeamService teamService;
//
//    @GetMapping("/groups/{groupId}")
//    public String show(@PathVariable Long groupId, Model model) {
//        GroupDto dto = groupService.getGroup(groupId);
//        List<TeamDto> teamList = teamService.getTeamsOnGroup(groupId);
//        model.addAttribute("groupname", dto.getName());
//        model.addAttribute("teamDtoList", teamList);
//        return "groups/show";
//    }
//
//}
