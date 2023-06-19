package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트가 실행되고 끝날 때마다 한 번씩 실행
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }
    
    // @Test 붙인 후 만들었던 메소드 테스트 가능
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//      Assertions.assertEquals(member, result); // 매개변수 : 기댓값, 실제값
        // member(실제값)와 기댓값 비교
        assertThat(member).isEqualTo(result); // static import하여 Assertions 없이 사용 가능
    }

    @Test
    public void findByName() {
        // member1, 2 저장
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // Name 찾기 테스트
       Member result = repository.findByName("spring1").get(); // get으로 꺼내면 Optional 한 번 깔 수 있음

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }


    
}
