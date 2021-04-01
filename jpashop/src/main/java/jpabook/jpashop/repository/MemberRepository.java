package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {
    //스프링이 영속성 컨텍스트를 주입
    @PersistenceContext
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        //JPQL 쿼리
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public Member findByName (String name){
        //JPQL 쿼리
        try {
            return em.createQuery("select m from Member m where m.name = :name", Member.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }
}
