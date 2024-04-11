package com.example.firstproject.dto;

import com.example.firstproject.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@Setter
public class CoffeeDTO {
    private Long id;
    private String name;
    private Long price;
    private Long kcal;

    public Coffee toEntity() {return new Coffee(id, name, price, kcal);}
}
