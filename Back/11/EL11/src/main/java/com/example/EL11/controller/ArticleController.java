package com.example.EL11.controller;

import com.example.EL11.dto.ArticleForm;
import com.example.EL11.entity.Article;
import com.example.EL11.repository.ArticleRepository;
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
@Slf4j // Simple Logging Facade for Java 으로 로깅기능 사용(println사용 안할 수 있음)
public class ArticleController {
    @Autowired //스프링 부트가 미리 생성해 놓은 레파지토리 객체 주입함
    private ArticleRepository articleRepository; //필드 선언문도 필요함, save()는 자동사용가능해짐
    //스프링 부트에서는 객체를 만들지 않아도 자동으로 객체를 만들어줌

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create") //이 링크로 연결됨
    public String createArticle(ArticleForm form){ //폼 데이터를 dto로 받기, 객체 단위로 보면 이해 잘감
        log.info(form.toString()); // dto에 정보가 담겼는지 로깅

        //1. DTO를 엔티티로 변환
        Article article = form.toEntity(); // 빨간색 없애기 위해 해줘야하는 작업 숙지하기
        log.info(article.toString()); // 엔티티로 잘 변환되었는지 로깅

        //2. 레파지토리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString()); // DB에 잘 저장되었는지 로깅

        return "redirect:/articles/" + saved.getId(); //리다이렉트 정의 부분
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model md){
        log.info(id.toString());
        Article articleEntity = articleRepository.findById(id).orElse(null);
        md.addAttribute("article", articleEntity);
        return "articles/show";
    }

    @GetMapping("/articles") //목록을 메인 페이지로 바꾼것
    public String index(Model md){
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        md.addAttribute("articleList", articleEntityList);
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit") //id를 매개변수로 밑에 전달함
    public String edit(@PathVariable Long id, Model md){


        // 1. 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 모델에 데이터 등록하기
        md.addAttribute("article", articleEntity);

        // 3. 뷰 페이지 설정하기
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){ //dto로 받음
        log.info(form.toString());

        // 1. DTO를 엔티티로 변환하기
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        // 2. 엔티티를 DB에 저장하기
        // 2-1. DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 2-2. 기존 데이터 값을 갱신하기 (데이터가 있다면 갱신하는 조건문)
        if(target != null){
            articleRepository.save(articleEntity);
        }

        // 3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다.");

        // 1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);

        // 2. 대상 엔티티 삭제하기
        if(target != null){
            log.info("삭제할 대상 : " + target.toString());
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제되었습니다.");
        }
        else log.info("삭제할 대상이 존재하지 않습니다."); //오류 처리용, 삭제할 대상이 없을수도 있으므로


        // 3. 결과 페이지로 리다이렉트하기, 목록으로 돌아가면 됨
        return "redirect:/articles"; //이해가 안되면 위 함수에서 index함수 위 URL요청을 보기
    }
}


