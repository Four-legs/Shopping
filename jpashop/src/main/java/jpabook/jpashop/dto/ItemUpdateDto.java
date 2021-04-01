package jpabook.jpashop.dto;

import jpabook.jpashop.controller.BookForm;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemUpdateDto {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

    public ItemUpdateDto(BookForm form){
        this.id = form.getId();
        this.name = form.getName();
        this.price = form.getPrice();
        this.stockQuantity = form.getStockQuantity();

        this.author = form.getAuthor();
        this.isbn = form.getIsbn();
    }
}
