package com.example.firstproject.controller;

import com.example.firstproject.dto.CoffeeDTO;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CoffeeController {
    private final CoffeeRepository coffeeRepository;

    public CoffeeController(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    // Create
    @PostMapping("/api/coffee/create")
    public Coffee create(@RequestBody CoffeeDTO dto) {
        Coffee res = coffeeRepository.save(dto.toEntity());
        if (res != null) {
            return res;
        }
        return null;
    }

    // Read
    @GetMapping("/api/coffees")
    public List<Coffee> list() {
        List<Coffee> coffees = coffeeRepository.findAll();
        return coffees;
    }


    // Update
    @PostMapping("/api/coffee/update")
    public Coffee update(@RequestBody CoffeeDTO dto) {
        Coffee target = coffeeRepository.findById(dto.getId()).orElse(null);
        if (target != null) {
            Coffee res = coffeeRepository.save(dto.toEntity());
            return res;
        }
        return null;
    }

    // DELETE
    @GetMapping("/api/coffee/delete/{id}")
    public String delete(@PathVariable Long id) {
        Coffee input = coffeeRepository.findById(id).orElse(null);
        if (input != null) {
            coffeeRepository.delete(input);
            return "삭제 성공";
        }
        return "삭제 실패";
    }
}
