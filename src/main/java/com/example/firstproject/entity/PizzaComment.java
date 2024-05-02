package com.example.firstproject.entity;

import com.example.firstproject.dto.PizzaCommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자
@NoArgsConstructor // 매개변수가 없는 기본 생성자 자동 생성
@Table(name="pizza_comment")
public class PizzaComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @ManyToOne // 댓글과 게시글 다대일 관계
    @JoinColumn(name="id") // 매핑할 외래키 지정
    private Pizza pizza; // 해당 댓글의 부모 게시글

    private String nickname;

    private String body;

    public static PizzaComment createPizzaComment(PizzaCommentDto dto, Pizza pizza) {
        // 예외 발생
        if (dto.getComment_id() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        if (dto.getPizzaId() != pizza.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 피자의 id가 잘못되었습니다.");

        // 엔티티 생성 및 반환
        return new PizzaComment(
                dto.getComment_id(), // 댓글 아이디
                pizza,
                dto.getNickname(), // 댓글 닉네임
                dto.getBody() // 댓글 본문
        );
    }

    public void patch(PizzaCommentDto dto) {
        if (this.comment_id != dto.getComment_id()) // 가져온 id와 수정할 id가 서로 다른 경우
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력됐습니다.");

        // 객체 갱신
        if (this.getNickname() != null) // 수정할 닉네임 데이터가 있다면
            this.nickname = dto.getNickname(); // 내용을 반영

        if (this.getBody() != null) // 수정할 본문 데이터가 있다면
            this.body = dto.getBody(); // 내용을 반영
    }
}
