package com.example.Ex3;

import com.example.Ex3.dto.ArticleForm;
import com.example.Ex3.entity.Article;
import com.example.Ex3.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    @Autowired //스프링 부트가 미리 생성해 놓은 레파지토리 객체 주입함
    private ArticleRepository articleRepository; //필드 선언문도 필요함, save()는 자동사용가능해짐
    //스프링 부트에서는 객체를 만들지 않아도 자동으로 객체를 만들어줌

    @GetMapping("/new")
    public String newArticleForm(){
        return "new";
    }

    @PostMapping("/create") //이 링크로 연결됨
    public String createArticle(ArticleForm form){ //폼 데이터를 dto로 받기, 객체 단위로 보면 이해 잘감
        System.out.println(form.toString()); //dto에 정보가 담겼는지 확인용
        //1. DTO를 엔티티로 변환
        Article article = form.toEntity(); //빨간색 없애기 위해 해줘야하는 작업 숙지하기
        System.out.println(article.toString()); //엔티티로 잘 변환되었는지 확인용
        //2. 레파지토리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString()); //DB에 잘 저장되었는지 확인용
        return "";
    }
}


