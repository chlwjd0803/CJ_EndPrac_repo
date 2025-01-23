package com.example.Template.service;

import com.example.Template.dto.CommentDto;
import com.example.Template.entity.Article;
import com.example.Template.entity.Comment;
import com.example.Template.repository.ArticleRepository;
import com.example.Template.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository; //댓글
    @Autowired
    private ArticleRepository articleRepository; //게시물

    public List<CommentDto> comments(Long articleId) {
//        // 1. 댓글 조회
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//        // 2. 엔티티 -> DTO 변환
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for(int i = 0; i < comments.size(); i++){
//            Comment c = comments.get(i); //엔티티 받아오기
//            CommentDto dto = CommentDto.createCommentDto(c); //엔티티를 dto로 변환
//            dtos.add(dto); //dtos배열에 저장
//        }
//        // 3. 결과 반환
//        return dtos; //댓글들이 모이면 반환하는 것임

        //한번에 해결하는 법인데..., 댓글 엔티티 목록 조회
        return commentRepository.findByArticleId(articleId).stream() // 엔티티 목록을 스트림으로 변환
                .map(comment -> CommentDto.createCommentDto(comment)) // 엔티티를 DTO로 매핑
                .collect(Collectors.toList()); // 스트림을 리스트로 변환
    }

    @Transactional //생성 중에 예외가 발생하면 롤백해야하므로 선언해야함
    public CommentDto create(Long articleId, CommentDto dto) {
        // 1. 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));
        // 2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);

        // 3. 댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);

        // 4. DTO로 변환해 반환
        return CommentDto.createCommentDto(created);


    }

    @Transactional //마찬가지로 예외가 발생하면 롤백해야하므로 선언해야함
    public CommentDto update(Long id, CommentDto dto) {
        // 1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));
        // 2. 댓글 수정
        target.patch(dto);
        // 3. DB로 갱신
        Comment updated = commentRepository.save(target);
        // 4. 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional //얘도 예외가 발생할 수 있기에 롤백을 대비해야함
    public CommentDto delete(Long id) {
        // 1. 댓글조회 및 예외발생
        Comment target = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다.")
        );
        // 2. 댓글 삭제
        commentRepository.delete(target);
        // 3. 삭제 댓글을 DTO로 변환 및 반환
        return CommentDto.createCommentDto(target);
    }
}
