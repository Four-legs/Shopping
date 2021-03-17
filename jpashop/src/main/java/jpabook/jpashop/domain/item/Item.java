package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")  //구분 칼럼
public abstract class Item {

    @Id @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;  //남은 수량

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//
    //도메인 주도 설계 시 엔티티 자체에서 해결할 수 있는 로직은
    //엔티티 안에서 구현을 하면 좋다.

    //재고량 증가
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    //재고량 감소
    public void removeStock(int quantity){
        if(stockQuantity < quantity){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity -= quantity;
    }
}
