package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id; // 수정 폼에서도 input 으로 id를 추가했으므로 DTO에도 추가
    private String title; // 제목을 받을 필드
    private String content; // 내용을 받을 필드


    public Article toEntity() {
        return new Article(id, title, content);
    }
}