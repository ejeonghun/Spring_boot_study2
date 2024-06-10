package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.dto.MemberCommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.entity.Member;
import com.example.firstproject.entity.MemberComment;
import com.example.firstproject.repository.MemberCommentRepository;
import com.example.firstproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberCommentService {

    @Autowired
    private MemberCommentRepository memberCommentRepository;

    @Autowired
    private MemberRepository memberRepository;
    // 댓글을 생성할 때 대상 댓글의 존재 여부 파악 위해

    // 댓글 조회
    public List<MemberCommentDto> comments(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("멤버가 없음"));

        // 결과 반환
        return memberCommentRepository.findById(member.getMemberId())
                .stream()
                .map(comment -> MemberCommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    // 댓글 생성
    @Transactional // 문제 발생 시 롤백
    public MemberComment create(Long memberId, MemberComment dto) {
        // 1. 게시글 조회 및 예외 발생
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException(("댓글 생성 실패!"
                        + "대상 회원이 없습니다.")));

        // 2. 댓글 엔티티 생성
        MemberComment comment = new MemberComment(null, dto.getBody(), dto.getNickname(), member);

        // 3. 댓글 엔티티를 DB로 저장
        MemberComment created = memberCommentRepository.save(comment);

        // DTO로 변환하여 반환
        return created;
    }

    // 댓글 수정
    @Transactional // 문제 발생 시 롤백
    public MemberComment update(Long commentId, MemberCommentDto dto) {
        // 댓글 조회 및 예외 발생
        MemberComment target = memberCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패 대상 댓글이 없습니다."));


        // 댓글 수정
        target.patch(dto); // Comment Entity 내부에서 검증 로직 추가

        // DB로 갱신
        MemberComment updated = memberCommentRepository.save(target);

        // 댓글 엔티티를 DTO로 변환 및 반환
        return updated; // Entity -> Dto 변환
    }
//
//
//    // 댓글 삭제
    @Transactional
    public MemberComment delete(Long id) {
        // 댓글 조회 및 예외 발생
        MemberComment target = memberCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패 대상 댓글이 없습니다."));

        // 댓글 삭제
        memberCommentRepository.delete(target);
        // 삭제 댓글을 DTO로 변환 및 반환
        return target;
    }
}
