package com.example.firstproject.controller;

import com.example.firstproject.dto.PizzaCommentDto;
import com.example.firstproject.service.PizzaCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaCommentApiController {

    @Autowired
    private PizzaCommentService PizzaCommentService;

    // 댓글 조회
    @GetMapping("/api/pizza/{pizzaId}/comments")
    public ResponseEntity<List<PizzaCommentDto>> comments (@PathVariable Long pizzaId) {
        // Service의 댓글 조회 메서드 호출
        List<PizzaCommentDto> dtos = PizzaCommentService.PizzaComments(pizzaId);

        // 결과 응답. 조회 실패시 스프링부트가 예외처리 한다고 가정 성공시에만 응답 보내도록 작성
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    // 댓글 생성
    @PostMapping("/api/pizza/{pizzaId}/comments")
    public ResponseEntity<PizzaCommentDto> create(@PathVariable Long pizzaId,
                                             @RequestBody PizzaCommentDto dto) {
        PizzaCommentDto createdDto = PizzaCommentService.create(pizzaId, dto);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    // 댓글 수정
    @PatchMapping("/api/pizza/comments/{id}")
    public ResponseEntity<PizzaCommentDto> update(@PathVariable Long id,
                                             @RequestBody PizzaCommentDto dto) {
        // 서비스에서 처리
        PizzaCommentDto updatedDto = PizzaCommentService.update(id, dto);

        // 결과응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    // 댓글 삭제
    @DeleteMapping("/api/pizza/comments/{id}")
    public ResponseEntity<PizzaCommentDto> delete(@PathVariable Long id) {

        // 서비스에서 처리
        PizzaCommentDto deletedDto = PizzaCommentService.delete(id);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }
}
