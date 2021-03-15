package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name="category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name="category_item",
            joinColumns = @JoinColumn(name="category_id"),      //중간 테이블의 category 쪽으로 들어가는 칼럼 지정
            inverseJoinColumns = @JoinColumn(name="item_id"))   //중간 테이블의 item 쪽으로 들어가는 칼럼 지정
    private List<Item> items = new ArrayList<>();

    //카테고리 계층 구조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    //==연관관계 메소드==//
    public void addChildCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }
}
