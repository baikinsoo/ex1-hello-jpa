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
        try{

            // 저장
            Team team1 = new Team();
            team1.setName("TeamA");
            em.persist(team1);

//            Team team2 = new Team();
//            team2.setName("TeamB");
//            em.persist(team2);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team1);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }


            // DB 확인용 코드
//            Team findTeam = findMember.getTeam();
//            System.out.println("findTeam.getName() = " + findTeam.getName());
//
//            //
//            Team newTeam = em.find(Team.class, team2.getId());
//            findMember.setTeam(newTeam);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
