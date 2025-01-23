package com.example.Template.service;

import com.example.Template.dto.PizzaDto;
import com.example.Template.entity.Pizza;
import com.example.Template.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Transactional
    public PizzaDto create(PizzaDto dto) {
        // 1. 정보 누락 예외검사
        if(dto.getName() == null || dto.getPrice() == null)
            throw new IllegalArgumentException("입력하지 않은 것이 있습니다.");

        // 2. 피자 엔티티 생성
        Pizza pizza = Pizza.createPizza(dto);

        // 3. 피자 엔티티를 DB에 저장
        Pizza created = pizzaRepository.save(pizza);

        // 4. DTO로 변환해 반환
        return PizzaDto.createPizzaDto(created);
    }

    public List<PizzaDto> pizzaList() {
        List<Pizza> pizza = pizzaRepository.findAll();

        List<PizzaDto> dtos = new ArrayList<PizzaDto>();
        for(var i=0; i<pizza.size(); i++) {
            Pizza p = pizza.get(i);
            PizzaDto dto = PizzaDto.createPizzaDto(p);
            dtos.add(dto);
        }
        return dtos;
    }

    public PizzaDto pizzaIndex(Long id) {
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id는 존재하지 않습니다.")
        );

        return PizzaDto.createPizzaDto(pizza);
    }

    @Transactional
    public PizzaDto update(Long id, PizzaDto dto) {
        Pizza target = pizzaRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("수정할 피자 데이터가 존재하지 않습니다.")
        );
        target.patch(dto);
        Pizza updated = pizzaRepository.save(target); //업데이트 된 엔티티 기반 저장
        return PizzaDto.createPizzaDto(updated);
    }

    @Transactional
    public PizzaDto delete(Long id) {
        Pizza target = pizzaRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("삭제할 피자 데이터가 존재하지 않습니다.")
        );
        pizzaRepository.delete(target);
        return PizzaDto.createPizzaDto(target);
    }
}
