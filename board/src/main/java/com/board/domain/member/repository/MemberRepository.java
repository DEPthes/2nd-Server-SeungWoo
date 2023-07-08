package com.board.domain.member.repository;

import com.board.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

/*
   save(entity): 엔티티를 저장하거나 업데이트
   findById(id): 주어진 식별자(id)로 엔티티를 조회
   findAll(): 모든 엔티티를 조회
   delete(entity): 엔티티를 삭제
   deleteById(id): 주어진 식별자(id)로 엔티티를 삭제
*/


}