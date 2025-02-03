package com.example.MusicStreaming.controller;

import com.example.MusicStreaming.entity.Music;
import com.example.MusicStreaming.repository.MusicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class MusicController {
    @Autowired
    private MusicRepository musicRepository;

    @GetMapping("/musics")
    public String list(Model md){
        List<Music> musicEntityList = musicRepository.findAll();
        md.addAttribute("musics", musicEntityList);
        return "musics/list";
    }
}
