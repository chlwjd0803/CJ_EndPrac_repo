package com.example.EL9.repository;

import com.example.EL9.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface MemberRepository extends CrudRepository<Member, Long> {
    @Override
    ArrayList<Member> findAll();
}
