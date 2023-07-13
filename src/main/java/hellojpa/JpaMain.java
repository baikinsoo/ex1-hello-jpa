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

            Team team1 = new Team();
            team1.setName("TeamA");
//            team1.getMembers().add(member);
            em.persist(team1);

            Member member = new Member();
            member.setUsername("member1");
//            member.changeTeam(team1); //** team1.addMember(member);를 쓰거나 두 군데 중에 한군데 넣는다.
            em.persist(member);

            team1.addMember(member);

//            team1.getMembers().add(member); //** -> 해당 코드를 setTeam에 코드를 추가한다.
            // 아래 flush, clear가 없이 현재 이 구문이 없으면 DB에서 조회하는 것이 아닌
            // 캐시에서 데이터를 가져오기 때문에 데이터가 없다. 때문에 위와 같이 member 값을 넣어줘야 한다.
            // 즉, 항상 양쪽에 값을 설정해야 한다.
            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team1.getId());
            List<Member> members = findTeam.getMembers();

            System.out.println("====================");
            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }
            System.out.println("====================");
            
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
