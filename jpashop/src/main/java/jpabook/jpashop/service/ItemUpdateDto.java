package jpabook.jpashop.service;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemUpdateDto {

    private Long id;
    private String Name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;
}
