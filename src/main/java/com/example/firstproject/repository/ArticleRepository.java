package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override // 상위 클래스 메서드를 하위 클래스가 재정의해서 사용
    ArrayList<Article> findAll(); // Iterable -> ArrayList 로 수정
}
