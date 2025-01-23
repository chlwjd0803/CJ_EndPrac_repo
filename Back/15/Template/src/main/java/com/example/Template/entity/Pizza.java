package com.example.Template.entity;

import com.example.Template.dto.PizzaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String price;

    public static Pizza createPizza(PizzaDto dto) {
        if(dto.getId() != null)
            throw new IllegalArgumentException("신규 데이터의 id는 없어야 합니다.");

        return new Pizza(dto.getId(), dto.getName(), dto.getPrice());
    }

    public void patch(PizzaDto dto) {
        if(this.id != dto.getId())
            throw new IllegalArgumentException("id가 일치하지 않습니다.");
        if(dto.getName() != null)
            this.name = dto.getName();
        if(dto.getPrice() != null)
            this.price = dto.getPrice();
    }
}
