package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //EntityManagerFactory를 만드는 순간 DB랑 연결도 되고 왠만한게 다 된다.
        //DB와 관련된 code 작성
        //위의 코드들은 생성하는 역활이기 때문에 최초 한 번만 작성되면 된다.
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // JPA는 트랜잭션이 반드시 있어야 한다.
        try {


            //----------------------------------
//            Child child1 = new Child();
//            Child child2 = new Child();
//
//            Parent parent = new Parent();
//            parent.addChild(child1);
//            parent.addChild(child2);
//
//            em.persist(parent);
//
//            Parent findParent = em.find(Parent.class, parent.getId());
////            findParent.getChildList().remove(0);
//            em.remove(parent);

//-------------------------------------
//            // LAZY 예제
//            Team teamA = new Team();
//            teamA.setName("TeamA");
//            em.persist(teamA);
//
////            Team teamB = new Team();
////            teamB.setName("TeamB");
////            em.persist(teamB);
//
//
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setTeam(teamA);
//            em.persist(member);
//
////            Member member2 = new Member();
////            member2.setUsername("member2");
////            member2.setTeam(teamB);
////            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
//            Member m = em.find(Member.class, member.getId());
////            System.out.println("m.getTeam().getClass() = " + m.getTeam().getClass());
////            List<Member> members = em.createQuery("select m from Member m", Member.class)
////                    .getResultList();
//
////            m.getTeam().getName();
////            System.out.println("==============");
////            m.getTeam().getName();
////            System.out.println("==============");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }



   /* private static void printMember(Member member) {
        System.out.println("member = " + member.getUsername());
    }
    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team = " + team);
    }*/
}
