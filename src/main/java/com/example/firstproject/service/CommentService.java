package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;
    // 댓글을 생성할 때 대상 게시글(article)의 존재 여부 파악 위해

    // 댓글 조회
    public List<CommentDto> comments(Long articleId) {
//        // 게시글 Id로 댓글 조회
//        List<Comment> comments = commentRepository.findByArticleID(articleId);
//
//        // 엔티티 -> DTO 변환
//        List<CommentDto> dtos = new ArrayList<CommentDto>(); // 빈 ArrayList 생성
//
////        for(int i=0; i < comments.size(); i++) {// 조회한 댓글 엔티티 수 만큼 반복
////            Comment c = comments.get(i); // 조회한 댓글 엔티티 하나씩 가져오기
////            CommentDto dto = CommentDto.createCommentDto(c); // 엔티티를 dto로 변환
////            dtos.add(dto);
////        }

        // 결과 반환
        return commentRepository.findByArticleID(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    // 댓글 생성
    @Transactional // 문제 발생 시 롤백
    public CommentDto create(Long articleId, CommentDto dto) {
        // 1. 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException(("댓글 생성 실패!"
                        + "대상 게시글이 없습니다.")));

        // 2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);

        // 3. 댓글 엔티티를 DB로 저장
        Comment created = commentRepository.save(comment);

        // DTO로 변환하여 반환
        return CommentDto.createCommentDto(created);
    }

    // 댓글 수정
    @Transactional // 문제 발생 시 롤백
    public CommentDto update(Long commentId, CommentDto dto) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패 대상 댓글이 없습니다."));


        // 댓글 수정
        target.patch(dto); // Comment Entity 내부에서 검증 로직 추가

        // DB로 갱신
        Comment updated = commentRepository.save(target);

        // 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated); // Entity -> Dto 변환
    }


    // 댓글 삭제
    @Transactional
    public CommentDto delete(Long id) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패 대상 댓글이 없습니다."));

        // 댓글 삭제
        commentRepository.delete(target);
        // 삭제 댓글을 DTO로 변환 및 반환
        return CommentDto.createCommentDto(target);
    }
}
