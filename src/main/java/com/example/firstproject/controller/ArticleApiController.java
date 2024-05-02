package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired // 의존성 주입 new ArticleService(); 를 스프링부트에서 자동으로 해줌
    private ArticleService articleService;

    // GET 구현 - 모든 게시글 조회
    @GetMapping("/api/articles") // GET /api/articles 접속 시
    public List<Article> index() {return articleService.index();}

    // GET 구현 - 단일 게시글 조회
    @GetMapping("/api/articles/{id}") // GET /api/articles/id 로 접속 시 해당 Id 게시글 조회
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    // POST 구현 - 데이터 생성 요청
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto); // Service에서 받은 Article 객체
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // body가 없어서 바로 ResponseEntity 생성
    } // 삼항 연산자 (조건) ? 참 값 : 거짓 값;

    // Patch 구현 - 게시글 수정 요청
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE 구현 - 데이터 삭제 요청
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null) ? // 삭제가 됐다면??
                ResponseEntity.status(HttpStatus.NO_CONTENT).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) {
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null) ?
            ResponseEntity.status(HttpStatus.OK).body(createdList):
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
