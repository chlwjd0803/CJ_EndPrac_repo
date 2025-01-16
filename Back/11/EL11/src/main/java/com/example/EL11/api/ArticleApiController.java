package com.example.EL11.api;

import com.example.EL11.dto.ArticleForm;
import com.example.EL11.entity.Article;
import com.example.EL11.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {

    @Autowired
    private ArticleRepository articleRepository;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }

    //POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){ //해당 어노테이션이 REST API에서 받아오는 걸 매개변수로 씀
        Article articleEntity = dto.toEntity();
        return articleRepository.save(articleEntity);
    }

    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            //상태가 잘못되었다는 것과 함께 의미없는 null값 전달
        }

        // 4. 업데이트 및 정상응답(200)하기
        target.patch(articleEntity); //필요한 부분만 수정되면 target으로 전달해야함
        Article updated = articleRepository.save(target); //엔티티 DB에 저장, 여기선 변수도 만들어 줘야 할듯함
        return ResponseEntity.status(HttpStatus.OK).body(updated); //정상 응답
    }

    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 2. 잘못된 요청 처리하기
        if(target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 3. 대상 삭제하기
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).body(null); //삭제했기때문에 null
        // return ResponseEntity.status(HttpStatus.OK).build(); //도 같음
    }

}
