package com.example.Ex3.dto;

import com.example.Ex3.entity.Article;

public class ArticleForm {
    private String title; //제목을 받을 필드
    private String content; //내용을 받을 필드

    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    //DTO에서 Entity로 바꾸므로 해당 코드에 작성됨
    public Article toEntity() {
        return new Article(null, title, content); //새 객체에 엔티티를 만들어주면됨
    }
}
