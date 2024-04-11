package com.example.firstproject.entity;


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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 댓글과 게시글 다대일 관계
    @JoinColumn(name="article_id") // 매핑할 외래키 지정
    private Article article; // 해당 댓글의 부모 게시글

    private String nickname;

    private String body;
}
