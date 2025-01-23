package com.example.Template.api;

import com.example.Template.dto.PizzaDto;
import com.example.Template.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaApiController {
    @Autowired
    private PizzaService pizzaService;

    // 1. 피자 데이터의 생성
    @PostMapping("/api/pizzas")
    public ResponseEntity<PizzaDto> create(@RequestBody PizzaDto dto) {
        // 서비스에 위임
        PizzaDto pizzaDto = pizzaService.create(dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(pizzaDto);
    }

    // 2. 피자 데이터의 목록 조회
    @GetMapping("/api/pizzas")
    public ResponseEntity<List<PizzaDto>> pizzas() {
        // 서비스에 위임
        List<PizzaDto> dtos = pizzaService.pizzaList();

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 3. 피자 데이터의 단일 조회
    @GetMapping("/api/pizzas/{id}")
    public ResponseEntity<PizzaDto> pizza(@PathVariable Long id) {
        // 서비스에 위임
        PizzaDto pizzaDto = pizzaService.pizzaIndex(id);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(pizzaDto);
    }

    // 4. 피자 데이터의 수정
    @PatchMapping("/api/pizzas/{id}")
    public ResponseEntity<PizzaDto> update(@PathVariable Long id, @RequestBody PizzaDto dto) {
        // 서비스에 위임
        PizzaDto update = pizzaService.update(id, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    // 5. 피자 데이터의 삭제
    @DeleteMapping("/api/pizzas/{id}")
    public ResponseEntity<PizzaDto> delete(@PathVariable Long id) {
        // 서비스에 위임
        PizzaDto delete = pizzaService.delete(id);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(delete);
    }
}
