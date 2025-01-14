package com.example.Lab3.repository;

import com.example.Lab3.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
