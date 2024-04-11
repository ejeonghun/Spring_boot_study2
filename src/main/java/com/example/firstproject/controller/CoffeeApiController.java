package com.example.firstproject.controller;

import com.example.firstproject.dto.CoffeeDTO;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CoffeeApiController {
    @Autowired
    private CoffeeRepository coffeeRepository;

    // GET 요청 - 전체 데이터 조회
    @GetMapping("/api/coffee/list")
    public List<Coffee> list() {
        return coffeeRepository.findAll();
    }

    // GET 요청 - 단일 데이터 조회
    @GetMapping("/api/coffee/{id}")
    public Coffee show(@PathVariable Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    // POST 요청 - 데이터 생성
    @PostMapping("/api/coffee")
    public Coffee save(CoffeeDTO dto) {
        Coffee target = dto.toEntity();
        return coffeeRepository.save(target);
    }

    // PATCH 요청 - 데이터 수정
    @PatchMapping("/api/coffee/{id}")
    public ResponseEntity<Coffee> edit(@PathVariable Long id, @RequestBody CoffeeDTO dto) {

        // 1. DTO -> 엔티티 변환
        Coffee coffee = dto.toEntity();

        // 2. 타깃 조회
        Coffee target = coffeeRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리하기
        if (target == null || target.getId() != coffee.getId()) {
            log.info("잘못된 요청 id: {}", id);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 4. 업데이트 및 200코드 처리
        target.patch(coffee);

        Coffee updated = coffeeRepository.save(coffee);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE 요청 - 데이터 삭제
}
