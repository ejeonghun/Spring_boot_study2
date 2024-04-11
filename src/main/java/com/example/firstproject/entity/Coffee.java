package com.example.firstproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id 자동 생성
    private Long id;

    private String name;

    private Long price;

    private Long kcal;


    public void patch(Coffee coffee) {
        if(coffee.name != null) {
            this.name = coffee.name;
        }

        if(coffee.price != null) {
            this.price = coffee.price;
        }

        if(coffee.kcal != null) {
            this.kcal = coffee.kcal;
        }
    }
}
