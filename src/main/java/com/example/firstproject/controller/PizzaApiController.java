package com.example.firstproject.controller;

import com.example.firstproject.dto.PizzaDto;
import com.example.firstproject.entity.Pizza;
import com.example.firstproject.service.PizzaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class PizzaApiController {
    @Autowired
    private PizzaService PizzaService;

    // GET 구현 - 모든 게시글 조회
    @GetMapping("/api/pizza")
    public List<Pizza> index() {
        return PizzaService.index();
    }

    // GET 구현 - 단일 게시글 조회
    @GetMapping("/api/pizza/{id}")
    public Pizza show(@PathVariable Long id) {
        return PizzaService.show(id);
    }

    // POST 구현 - 데이터 생성 요청
    @PostMapping("/api/pizza")
    public ResponseEntity<Pizza> create(@RequestBody PizzaDto dto) {
        Pizza created = PizzaService.create(dto); // Service에서 받은 Pizza 객체
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    } // 삼항 연산자 (조건) ? 참 값 : 거짓 값;

    // Patch 구현 - 게시글 수정 요청
    @PatchMapping("/api/pizza/{id}")
    public ResponseEntity<Pizza> update(@PathVariable Long id, @RequestBody PizzaDto dto) {
        Pizza updated = PizzaService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE 구현 - 데이터 삭제 요청
    @DeleteMapping("/api/pizza/{id}")
    public ResponseEntity<Pizza> delete(@PathVariable Long id) {
        Pizza deleted = PizzaService.delete(id);
        return (deleted != null) ? // 삭제가 됐다면??
                ResponseEntity.status(HttpStatus.NO_CONTENT).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
