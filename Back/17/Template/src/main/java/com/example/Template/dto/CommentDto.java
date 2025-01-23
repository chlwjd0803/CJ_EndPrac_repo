package com.example.Template.dto;

import com.example.Template.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class CommentDto {
    private Long id;
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(), //댓글 엔티티의 id
                comment.getArticle().getId(), // 댓글이 속한 게시물의 id
                comment.getNickname(), // 닉네임 겟
                comment.getBody() // 댓글 내용 겟
        );
    }
}
