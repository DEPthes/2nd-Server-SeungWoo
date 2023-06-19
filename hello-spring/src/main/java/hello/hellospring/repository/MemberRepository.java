package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    // Optional : 반환값이 null일 수 있는데, 그러한 null을 처리하는 방법 중 하나로, 요즘 자주 사용한다고 함
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
