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
@Table(name = "pizza")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    public void patch(Pizza dto) {
        if (this.id != dto.getId()) // 가져온 id와 수정할 id가 서로 다른 경우
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력됐습니다.");

        // 객체 갱신
        if (this.getName() != null) // 수정할 이름이 있다면
            this.name = dto.getName(); // 내용을 반영

        if (this.getPrice() != null) // 수정할 가격이 있다면
            this.price = dto.getPrice(); // 내용을 반영
    }
}
