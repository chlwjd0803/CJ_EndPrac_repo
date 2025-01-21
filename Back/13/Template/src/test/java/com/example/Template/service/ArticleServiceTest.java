package com.example.Template.service;

import com.example.Template.dto.ArticleForm;
import com.example.Template.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        // 1. 예상 데이터
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));
        // 2. 실제 데이터
        List<Article> articles = articleService.index();
        // 3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString()); //문자열로 변환하여 테스트
    }

    @Test
    void show_success_존재하는_id_입력() {
        // 1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");
        // 2. 실제 데이터
        Article article = articleService.show(id);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_fail_존재하지않는_id_입력() {
        // 1. 예상 데이터
        Long id = -1L;
        Article expected = null;
        // 2. 실제 데이터
        Article article = articleService.show(id); //-1이 들어가므로 null이어야 정상
        // 3. 비교 및 검증
        assertEquals(expected, article); //둘다 null을 반환할 예정이기에 toString은 못씀
    }

    @Test
    @Transactional //테스트 후 데이터를 롤백함
    void create_success_title과_content만_있는_dto_입력() {
        // 1. 예상 데이터
        String title = "라라라라";
        String content = "4444";
        Article expected = new Article(4L, title, content);
        // 2. 실제 데이터
        ArticleForm dto = new ArticleForm(null, title, content); //id 자동배정
        Article article = articleService.create(dto);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional //테스트 후 데이터를 롤백함
    void create_fail_실패_id가_포함된_dto_입력() {
        // 1. 예상 데이터
        Long id = 4L;
        String title = "라라라라";
        String content = "4444";
        Article expected = null;

        // 2. 실제 데이터
        ArticleForm dto = new ArticleForm(id, title, content);
        Article article = articleService.create(dto);

        // 3. 비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_success_존재하는_id와_title_content가_있는_dto_입력() {
        // 1. 예상 데이터
        Long id = 1L;
        String title = "AAAA";
        String content = "5678";
        Article expected = new Article(id, title, content);
        // 2. 실제 데이터
        ArticleForm dto = new ArticleForm(id, title, content);
        Article article = articleService.update(id, dto);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_success_존재하는_id와_title만_있는_dto_입력() {
        // 1. 예상 데이터
        Long id = 1L;
        String title = "ABCD";
        Article expected = new Article(id, title, "1111");

        // 2. 실제 데이터
        ArticleForm dto = new ArticleForm(id, title, null);
        Article article = articleService.update(id, dto);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_fail_존재하지_않는_id의_dto_입력() {
        // 1. 예상 데이터
        Long id = -1L;
        Article expected = null;

        // 2. 실제 데이터
        ArticleForm dto = new ArticleForm(id, null, null);
        Article article = articleService.update(id, dto);

        // 3. 비교 및 검즘
        assertEquals(expected, article);

    }

    @Test
    @Transactional
    void delete_success_존재하는_id_입력() {
        // 1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");

        // 2. 실제 데이터
        Article article = articleService.delete(id);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());

    }

    @Test
    @Transactional
    void delete_fail_존재하지않는_id_입력() {
        // 1. 예상 데이터
        Long id = -1L;
        Article expected = null;
        // 2. 실제 데이터
        Article article = articleService.delete(id);
        // 3. 비교 및 검증
        assertEquals(expected, article);
    }
}