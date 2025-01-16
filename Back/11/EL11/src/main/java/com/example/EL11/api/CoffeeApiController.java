package com.example.EL11.api;


import com.example.EL11.dto.CoffeeDto;
import com.example.EL11.entity.Coffee;
import com.example.EL11.repository.CoffeeRepository;
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

    @GetMapping("api/coffees")
    public List<Coffee> index(){
        return coffeeRepository.findAll();
    }
    @GetMapping("/api/coffees/{id}")
    public Coffee show(@PathVariable long id){
        return coffeeRepository.findById(id).orElse(null);
    }

    @PostMapping("/api/coffees")
    public Coffee create(@RequestBody CoffeeDto dto){
        Coffee coffeeEntity = dto.toEntity();
        return coffeeRepository.save(coffeeEntity);
    }

    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable long id, @RequestBody CoffeeDto dto){
        Coffee coffeeEntity = dto.toEntity();
        Coffee target = coffeeRepository.findById(id).orElse(null);

        if(target == null || target.getId() != coffeeEntity.getId())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        target.patch(coffeeEntity);
        Coffee updated = coffeeRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable long id){
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if(target == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        coffeeRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
