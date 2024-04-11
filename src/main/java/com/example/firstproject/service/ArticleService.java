package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleService {
    @Autowired // 게시글 리파지터리 객체 주입
    private ArticleRepository articleRepository;

    
    public List<Article> index() { // 전체 게시물 조회
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null ) { // id는 자동 생성되는데 id를 입력한다면 null 반환
            return null;
        }
        return articleRepository.save(article);
    }



    public Article update(Long id, ArticleForm dto) {
        // 1. DTO -> 엔티티 변환하기
        Article article = dto.toEntity();
        log.info(" id : {}, article:{} ", id, article.toString()); // 로그 출력

        // 2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);
        // 반환형이 ResponseEntity

        // 3. 잘못된 요청 처리하기
        if (target == null || id != article.getId()) {
            log.info("잘못된 요청! id : {}, article: {}", id, article.toString());

            // 400 에러 잘못된 요청 응답
            return null;
        }

        // 4. 업데이트 및 정상 응답 (200)하기
        target.patch(article);
        Article updated = articleRepository.save(article);
        return updated;
        // 수정된 데이터는 ResponseEntity에 담아서 보냄
        // 상태(status)에서 정상 응답(200) 또는 httpStatus.ok, 본문(body)에는 반환할 데이터인 updated를 싣는다.
    }

    public Article delete(Long id) {
        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 2. 잘못된 요청 처리하기
        if (target == null) {
            return null; // 응답은 컨트롤러가 하므로 여기서는 null 반환
        }

        // 3. 대상 삭제하기
        articleRepository.delete(target);
        return target; // db에서 삭제한 대상을 컨트롤러에 반환
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // 1. dto 묶음을 엔티티 묶음으로 변환하기
        List<Article> articleList = dtos.stream() // dtos를 스트림화함. 저장
                .map(dto -> dto.toEntity()) // map()으로 dto 하나하나를 entity
                .collect(Collectors.toList()); // 매핑한 것을 리스트로 묶음

        // 2. 엔티티 묶음을 DB에 저장하기
        articleList.stream() // articleList를 스트림화
                .forEach(article -> articleRepository.save(article));
        // article이 하나씩 올 때마다 db저장

        // 3. 강제 예외 발생시키기
        articleRepository.findById(-1L) // 존재하지 않는 id가 -1인 데이터 찾기
                .orElseThrow(() -> new IllegalArgumentException(" 결제 실패 ! "));
        // 찾는 데이터 없으면 예외 발생

        // 4. 결과값 반환하기
        return articleList;
    }
}
