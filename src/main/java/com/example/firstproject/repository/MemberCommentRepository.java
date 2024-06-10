package com.example.firstproject.repository;

import com.example.firstproject.entity.MemberComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCommentRepository extends JpaRepository<MemberComment, Long> {

}
