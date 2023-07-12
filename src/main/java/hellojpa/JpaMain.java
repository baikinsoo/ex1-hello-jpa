package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //EntityManagerFactory를 만드는 순간 DB랑 연결도 되고 왠만한게 다 된다.
        EntityManager em = emf.createEntityManager();
        //DB와 관련된 code 작성
        //위의 코드들은 생성하는 역활이기 때문에 최초 한 번만 작성되면 된다.

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // JPA는 트랜잭션이 반드시 있어야 한다.

        try { //try catch를 사용하지 않으면 중간에 예외가 발생했을 때 끝까지 실행되지 않을 수 있기 때문에
            //예외 상황을 생각하여 try catch로 묶어서 작성하는 것이 정석이다.

//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");

            //조회, 수정
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());
//            findMember.setName("HelloJPA");

            // JPQL
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            //삭제
//            em.refresh(findMember);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
