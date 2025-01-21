package com.example.Template.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne //다대일 관계
    @JoinColumn(name = "article_id") // 외래키 생성, Article 엔티티의 기본키id와 매핑
    private Article article; //댓글을 단 게시물
    @Column
    private String nickname; //댓글 단 사람
    @Column
    private String body; //댓글내용
}
