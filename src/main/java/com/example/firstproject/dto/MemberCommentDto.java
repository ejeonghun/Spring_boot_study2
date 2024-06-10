package com.example.firstproject.dto;

import com.example.firstproject.entity.Comment;
import com.example.firstproject.entity.MemberComment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberCommentDto {
    private Long commentId;

    private String body;

    private String nickname;

    private Long memberId;


    public static MemberCommentDto createCommentDto(MemberComment comment) {
        return new MemberCommentDto(
                comment.getCommentId(), // 댓글 엔티티의 id
                comment.getBody(), // 댓글 엔티티의 body
                comment.getNickname(), // 댓글 엔티티의 nickname
                comment.getMember().getMemberId() // 댓글 엔티티가 속한 부모 게시글의 id
        );
    }
}
