package com.example.firstproject.service;

import com.example.firstproject.dto.PizzaDto;
import com.example.firstproject.entity.Pizza;
import com.example.firstproject.repository.PizzaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PizzaService {
    @Autowired // 게시글 리파지터리 객체 주입
    private PizzaRepository PizzaRepository;


    public List<Pizza> index() { // 전체 게시물 조회
        return PizzaRepository.findAll();
    }

    public Pizza show(Long id) {
        return PizzaRepository.findById(id).orElse(null);
    }

    public Pizza create(PizzaDto dto) {
        Pizza pizza = dto.toEntity();
        if(pizza.getId() != null ) { // id는 자동 생성되는데 id를 입력한다면 null 반환
            return null;
        }
        return PizzaRepository.save(pizza);
    }



    public Pizza update(Long id, PizzaDto dto) {
        // 1. DTO -> 엔티티 변환하기
        Pizza pizza = dto.toEntity();
        log.info(" id : {}, Pizza:{} ", id, pizza.toString()); // 로그 출력

        // 2. 타깃 조회하기
        Pizza target = PizzaRepository.findById(id).orElse(null);
        // 반환형이 ResponseEntity

        // 3. 잘못된 요청 처리하기
        if (target == null || id != pizza.getId()) {
            log.info("잘못된 요청! id : {}, Pizza: {}", id, pizza.toString());

            // 400 에러 잘못된 요청 응답
            return null;
        }

        // 4. 업데이트 및 정상 응답 (200)하기
        target.patch(pizza);
        Pizza updated = PizzaRepository.save(pizza);
        return updated;
        // 수정된 데이터는 ResponseEntity에 담아서 보냄
        // 상태(status)에서 정상 응답(200) 또는 httpStatus.ok, 본문(body)에는 반환할 데이터인 updated를 싣는다.
    }

    public Pizza delete(Long id) {
        // 1. 대상 찾기
        Pizza target = PizzaRepository.findById(id).orElse(null);

        // 2. 잘못된 요청 처리하기
        if (target == null) {
            return null; // 응답은 컨트롤러가 하므로 여기서는 null 반환
        }

        // 3. 대상 삭제하기
        PizzaRepository.delete(target);
        return target; // db에서 삭제한 대상을 컨트롤러에 반환
    }

//    @Transactional
//    public List<Pizza> createPizzas(List<PizzaDto> dtos) {
//        // 1. dto 묶음을 엔티티 묶음으로 변환하기
//        List<Pizza> PizzaList = dtos.stream() // dtos를 스트림화함. 저장
//                .map(dto -> dto.toEntity()) // map()으로 dto 하나하나를 entity
//                .collect(Collectors.toList()); // 매핑한 것을 리스트로 묶음
//
//        // 2. 엔티티 묶음을 DB에 저장하기
//        PizzaList.stream() // PizzaList를 스트림화
//                .forEach(Pizza -> PizzaRepository.save(Pizza));
//        // Pizza이 하나씩 올 때마다 db저장
//
//        // 3. 강제 예외 발생시키기
//        PizzaRepository.findById(-1L) // 존재하지 않는 id가 -1인 데이터 찾기
//                .orElseThrow(() -> new IllegalArgumentException(" 결제 실패 ! "));
//        // 찾는 데이터 없으면 예외 발생
//
//        // 4. 결과값 반환하기
//        return PizzaList;
//    }
}
