package com.example.Ex4.dto;

import com.example.Ex4.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor //새 어노테이션을 추가하며 생성자 삭제
@ToString //새 어노테이션을 추가하며 오버라이딩 함수 삭제
public class ArticleForm {
    private String title; //제목을 받을 필드
    private String content; //내용을 받을 필드

    /*
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }
    */

    /*
    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
    */

    //DTO에서 Entity로 바꾸므로 해당 코드에 작성됨
    public Article toEntity() {
        return new Article(null, title, content); //새 객체에 엔티티를 만들어주면됨
    }
}
