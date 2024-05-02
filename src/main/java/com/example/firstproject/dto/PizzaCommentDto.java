package com.example.firstproject.dto;

import com.example.firstproject.entity.PizzaComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 모든 필드를 파라미터로 갖는 생성자 자동 생성
@NoArgsConstructor // 파라미터가 아예 없는 기본 생성자 자동 생성
@Getter // 각 필드 값을 조회할 수 있는 getter 메서드 자동 생성
@ToString // 모든 필드를 출력할 수 있는 toString 메서드 자동 생성
public class PizzaCommentDto {
    private Long comment_id; // 댓글의 id
    private Long pizzaId; // 댓글의 부모 id
    private String nickname; // 댓글 작성자
    private String body; // 댓글 본문


    public static PizzaCommentDto createCommentDto(PizzaComment comment) {
        return new PizzaCommentDto(
                comment.getComment_id(), // 댓글 엔티티의 id
                comment.getPizza().getId(), // 댓글 엔티티가 속한 부모 게시글의 id
                comment.getNickname(), // 댓글 엔티티의 nickname
                comment.getBody() // 댓글 엔티티의 body
        );
    }
}
