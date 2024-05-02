package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import com.example.firstproject.entity.PizzaComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PizzaCommentRepository extends JpaRepository<PizzaComment, Long> {
    // 특정 게시글의 모든 댓글 조회
    List<PizzaComment> findByPizzaId(Long pizzaId);

    // 득정 닉네임의 모든 댓글 조회
    List<PizzaComment> findByNickname(String nickname);
}
