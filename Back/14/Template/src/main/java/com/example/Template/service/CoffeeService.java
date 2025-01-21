package com.example.Template.service;

import com.example.Template.dto.CoffeeDto;
import com.example.Template.entity.Coffee;
import com.example.Template.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    public List<Coffee> index() {
        return coffeeRepository.findAll();
    }

    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeDto dto) {
        Coffee coffee = dto.toEntity();
        if(coffee.getId() != null) return null;
        return coffeeRepository.save(coffee);
    }

    public Coffee update(long id, CoffeeDto dto) {
        Coffee coffee = dto.toEntity();
        log.info("id : {}, coffee : {}", id, coffee.toString());

        Coffee target = coffeeRepository.findById(id).orElse(null);

        if(target == null || target.getId() != coffee.getId()){
            log.info("잘못된 요청 id : {}, coffee : {}", id, coffee.toString());
            return null;
        }

        target.patch(coffee);
        Coffee updated = coffeeRepository.save(target);
        return updated;
    }

    public Coffee delete(long id) {
        Coffee target = coffeeRepository.findById(id).orElse(null);

        if(target == null) return null;

        coffeeRepository.delete(target);
        return target;
    }
}
