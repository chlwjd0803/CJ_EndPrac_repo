package com.example.Template.api;

import com.example.Template.dto.CommentDto;
import com.example.Template.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;
    // 1. 댓글 조회
    @GetMapping("/api/articles/{articleId}/comments")
    ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        // 서비스에 위임
        List<CommentDto> dtos = commentService.comments(articleId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    // 2. 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto){
        // 서비스에 위임
        CommentDto createDto = commentService.create(articleId, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }

    // 3. 댓글 수정
    @PatchMapping("/api/comments/{id}") //이때의 id는 댓글의 것임을 주의할 것
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto){
        // 서비스에 위임
        CommentDto updateDto = commentService.update(id, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }

    // 4. 댓글 삭제
    @DeleteMapping("/api/comments/{id}") //이때도 댓글의 id를 지워야함
    ResponseEntity<CommentDto> delete(@PathVariable Long id){
        // 서비스에 위임
        CommentDto deleteDto = commentService.delete(id);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deleteDto);
    }
}
