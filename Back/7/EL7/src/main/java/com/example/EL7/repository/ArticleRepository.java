package com.example.EL7.repository;

import com.example.EL7.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

//<관리 대상 엔티티 클래스 타입, 관리 대상 엔티티의 대푯값 타입>
public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();
}
