package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        //현재 존재하지 않는 상품 (최초 등록 상품)
        //즉, 신규 상품에 Id값을 발급해주는 것
        if(item.getId() == null){
            em.persist(item);
        }
        else{
            //이미 존재하는 상품이라면 그냥 그 Id를 사용
            em.merge(item);
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
