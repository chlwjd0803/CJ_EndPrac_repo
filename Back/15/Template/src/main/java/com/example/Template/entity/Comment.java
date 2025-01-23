package com.example.Template.entity;

import com.example.Template.dto.CommentDto;
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

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외발생
        if(dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        if(dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 일치하지 않습니다.");

        // 엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        // 예외발생
        if(this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력되었습니다.");

        // 객체갱신
        if(dto.getNickname() != null) //수정할 닉네임 데이터가 있다면
            this.nickname = dto.getNickname(); //반영
        if(dto.getBody() != null) //수정할 댓글 내용이 있다면
            this.body = dto.getBody(); //반영

    }
}
