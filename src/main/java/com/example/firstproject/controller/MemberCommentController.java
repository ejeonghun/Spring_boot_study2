package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberCommentDto;
import com.example.firstproject.entity.MemberComment;
import com.example.firstproject.service.MemberCommentService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberCommentController {

    @Autowired
    private MemberCommentService memberCommentService;

    @GetMapping("/api/memberComments")
    public List<MemberCommentDto> getComments(@RequestParam Long memberId) {
        return memberCommentService.comments(memberId);
    }

    @PostMapping("/api/memberComments/{memberid}")
    public MemberComment createComment(@PathVariable Long memberid, @RequestBody MemberComment comment) {
        return memberCommentService.create(memberid, comment);
    }

    @PatchMapping("/api/memberComments/{commentid}")
    public ResponseEntity<MemberComment> updateComment(@PathVariable Long commentid, @RequestBody MemberCommentDto comment) {
        MemberComment member = memberCommentService.update(commentid, comment);
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

    @DeleteMapping("/api/memberComments/{commentid}")
    public ResponseEntity<MemberComment> deleteComment(@PathVariable Long commentid) {
        MemberComment member = memberCommentService.delete(commentid);
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }
}
