package com.example.Template.service;

import com.example.Template.dto.ArticleForm;
import com.example.Template.entity.Article;
import com.example.Template.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;


    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article articleEntity = dto.toEntity();
        if(articleEntity.getId() != null) return null;
        return articleRepository.save(articleEntity);
    }

    public Article update(Long id, ArticleForm dto) {
        // 데이터에 상태 코드까지 보내려면 ResponseEntity에 실어 보내야됨

        // 1. DTO를 엔티티로 변환
        Article articleEntity = dto.toEntity();
        log.info("id : {}, articleEntity : {}", id, articleEntity.toString());

        // 2. 타겟 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리
        if(target == null || target.getId() != articleEntity.getId()){
            //오류가 있는 것
            log.info("잘못된 요청 id : {}, articleEntity : {}", id, articleEntity.toString());
            return null;
            //상태가 잘못되었다는 것과 함께 의미없는 null값 전달
        }

        // 4. 업데이트 및 정상응답(200)하기
        target.patch(articleEntity); //필요한 부분만 수정되면 target으로 전달해야함
        Article updated = articleRepository.save(target); //엔티티 DB에 저장, 여기선 변수도 만들어 줘야 할듯함
        return updated; //정상 응답
    }

    public Article delete(Long id) {
        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 2. 잘못된 요청 처리하기
        if(target == null){
            return null;
        }

        // 3. 대상 삭제하기
        articleRepository.delete(target);
        return target;
    }
    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // 1. dto 묶음을 엔티티 묶음으로 변환하기
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        /* //밑에 코드를 위에 스트림 문법으로 줄임
        for(int i=0; i<dtos.size(); i++){
            ArticleForm dto = dtos.get(i);
            Article entity = dto.toEntity();
            articleList.add(entity);
        }
         */

        // 2. 엔티티 묶음을 DB에 저장하기
        articleList.stream().forEach(article -> articleRepository.save(article));
        /* //밑에 코드를 위에 스트림 문법으로 줄임
        for(int i=0; i<articleList.size(); i++){
            Article article = articleList.get(i);
            articleRepository.save(article)
        }
        */

        // 3. 강제 예외 발생시키기
        articleRepository.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("결제 실패!"));


        // 4. 결과 값 반환하기
        return articleList;
    }
}
