package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        // entityManager(em)를 꺼낸 후, 실제로 동작하는 코드를 작성
        
        EntityTransaction tx = em.getTransaction();
        tx.begin(); // 트랜잭션 시작

        try{
            // 회원 등록
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
            
//            em.persist(member); // member 저장

            // 회원 조회
//            Member findMember = em.find(Member.class, 1L);

            // 회원 수정
//            findMember.setName("HelloJPA");

            // 회원 삭제
//          em.remove(findMember); // 찾은 아이를 remove에 넣어주면 됨

            // JPQL
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            for(Member member : result){
                System.out.println("member.name = " + member.getName());
            }

            tx.commit(); // 트랜잭션 커밋
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close(); // 전체 애플리케이션이 끝나면 팩토리 종료
    }
}
