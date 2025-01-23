package com.example.Template.repository;

import com.example.Template.entity.Pizza;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PizzaRepository extends CrudRepository<Pizza, Long> {
    @Override
    ArrayList<Pizza> findAll();
}
