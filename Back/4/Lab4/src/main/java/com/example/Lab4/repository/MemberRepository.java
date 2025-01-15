package com.example.Lab4.repository;

import com.example.Lab4.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
