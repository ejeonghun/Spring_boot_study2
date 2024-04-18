package com.example.firstproject.repository;

import com.example.firstproject.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    @Override // 상위 클래스 메서드를 하위 클래스가 재정의해서 사용
    ArrayList<Pizza> findAll(); // Iterable -> ArrayList 로 수정
}
