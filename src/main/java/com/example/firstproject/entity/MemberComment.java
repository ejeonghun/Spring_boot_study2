package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.dto.MemberCommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class MemberComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String body;

    private String nickname;

    @ManyToOne // 댓글과 멤버아이디 다대일 관계
    @JoinColumn(name="member_id") // 매핑할 외래키 지정
    private Member member;

    public void patch(MemberCommentDto dto) {
        if (this.commentId != dto.getCommentId()) // 가져온 id와 수정할 id가 서로 다른 경우
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력됐습니다.");

        // 객체 갱신
        if (this.getNickname() != null) // 수정할 닉네임 데이터가 있다면
            this.nickname = dto.getNickname(); // 내용을 반영

        if (this.getBody() != null) // 수정할 본문 데이터가 있다면
            this.body = dto.getBody(); // 내용을 반영
    }
}
