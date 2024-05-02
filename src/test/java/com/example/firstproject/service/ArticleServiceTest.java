package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService; // Articles 클래스 테스트 하기 위해 articleService 객체 선언

    @Transactional
    @Test
    void create_4() {
        // 1. 예상 데이터
        String title = "라라라라";
        String content = "4444"; // id는 자동 생성됨
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        // 2. 실제 데이터
        Article article = articleService.create(dto);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Transactional
    @Test
    void create_5() { // id는 자동생성되는데 입력한 경우
        // 1. 예상 데이터
        Long id = 4L;
        String title = "라라라라";
        String content = "4444"; // id는 자동 생성되는데 입력한 경우
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;// new Article(4L, title, content);
        // ID는 자동 생성되는데

        // 2. 실제 데이터
        Article article = articleService.create(dto);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Transactional
    @Test
    void create_fail_test() {
        // 1. 예상 데이터
        Long id = 5L;
        String title = "널 테스트";
        String content = "4444";    //id 자동생성되는데 입력한 경우
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;   //id 넣으면 오류로 null 반환됨. 예상.

        // 2. 실제 데이터
        Article article = articleService.create(dto);

        // 3. 비교 및 검증
        assertEquals(expected, article);
    }


    @Test
    void index() {
        // 1. 예상 데이터
        Article a = new Article(1L, "aaaa", "1111");
        Article b = new Article(2L, "bbbb", "2222");
        Article c = new Article(3L, "cccc", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));

        // 2. 실제 데이터
        List<Article> articles = articleService.index();

        // 3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_1() {
        // 1. 예상 데이터
        Long id = 2L;
        Article expected = new Article(id, "bbbb", "2222");

        // 2. 실제 데이터
        Article article = articleService.show(id);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }



}