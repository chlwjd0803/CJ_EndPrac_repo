package com.example.EL11.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

//엔티티 클래스의 기본 형태
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article {
    @Id //엔티티의 대푯값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 생성기능 + data.sql 번호도 직접 해줌
    private Long id;
    @Column //각 필드 열과 연결됨
    private String title;
    @Column
    private String content;

    public void patch(Article articleEntity) {
        if(articleEntity.title != null)
            this.title = articleEntity.title;
        if(articleEntity.content != null)
            this.content = articleEntity.content;
        //컨트롤러에 의하면 this가 수정 대상을 가르킴
    }

    /* //혹은 게터 롬복 추가
    public Long getId() { //데이터 타입을 Long타입으로 변경해야함
        return id;
    }
    */

    /*
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
    */
}
