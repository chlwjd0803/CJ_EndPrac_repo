package com.example.Ex4.repository;

import com.example.Ex4.entity.Article;
import org.springframework.data.repository.CrudRepository;

//<관리 대상 엔티티 클래스 타입, 관리 대상 엔티티의 대푯값 타입>
public interface ArticleRepository extends CrudRepository<Article, Long> {
}
