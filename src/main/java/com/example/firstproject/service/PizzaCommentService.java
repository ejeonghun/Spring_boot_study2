package com.example.firstproject.service;

import com.example.firstproject.dto.PizzaCommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Pizza;
import com.example.firstproject.entity.PizzaComment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.PizzaCommentRepository;

import com.example.firstproject.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaCommentService {

    @Autowired
    private PizzaCommentRepository pizzaCommentRepository;

    @Autowired
    private PizzaRepository pizzaRepository;
    // 댓글을 생성할 때 대상 게시글(article)의 존재 여부 파악 위해

    // 댓글 조회
    public List<PizzaCommentDto> PizzaComments(Long articleId) {
        // 결과 반환
        return pizzaCommentRepository.findByPizzaId(articleId)
                .stream()
                .map(PizzaComment -> PizzaCommentDto.createCommentDto(PizzaComment))
                .collect(Collectors.toList());
    }

    // 댓글 생성
    @Transactional // 문제 발생 시 롤백
    public PizzaCommentDto create(Long pizza_Id, PizzaCommentDto dto) {
        // 1. 게시글 조회 및 예외 발생
        Pizza pizza = pizzaRepository.findById(pizza_Id)
                .orElseThrow(() -> new IllegalArgumentException(("댓글 생성 실패!"
                        + "대상 피자가 없습니다.")));

        // 2. 댓글 엔티티 생성
        PizzaComment pizzaComment = PizzaComment.createPizzaComment(dto, pizza);

        // 3. 댓글 엔티티를 DB로 저장
        PizzaComment created = pizzaCommentRepository.save(pizzaComment);

        // DTO로 변환하여 반환
        return PizzaCommentDto.createCommentDto(created);
    }

    // 댓글 수정
    @Transactional // 문제 발생 시 롤백
    public PizzaCommentDto update(Long PizzaCommentId, PizzaCommentDto dto) {
        // 댓글 조회 및 예외 발생
        PizzaComment target = pizzaCommentRepository.findById(PizzaCommentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패 대상 댓글이 없습니다."));


        // 댓글 수정
        target.patch(dto); // PizzaComment Entity 내부에서 검증 로직 추가

        // DB로 갱신
        PizzaComment updated = pizzaCommentRepository.save(target);

        // 댓글 엔티티를 DTO로 변환 및 반환
        return PizzaCommentDto.createCommentDto(updated); // Entity -> Dto 변환
    }


    // 댓글 삭제
    @Transactional
    public PizzaCommentDto delete(Long id) {
        // 댓글 조회 및 예외 발생
        PizzaComment target = pizzaCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패 대상 댓글이 없습니다."));

        // 댓글 삭제
        pizzaCommentRepository.delete(target);
        // 삭제 댓글을 DTO로 변환 및 반환
        return PizzaCommentDto.createCommentDto(target);
    }
}
