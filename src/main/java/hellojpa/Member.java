package hellojpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
//관례상 @Table로 테이블 명을 작성해주지 않으면 클래스명이 테이블 명이 된다.
public class Member {

    @Id
    private Long id;
    //@Colum을 통해서 Colum 이름도 테이블에 작성된 이름으로 작성할 수 있다.
    private String name;

    public Member() {
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
