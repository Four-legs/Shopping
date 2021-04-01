package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.dto.ItemUpdateDto;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    @Transactional
    public void updateItem(ItemUpdateDto itemUpdateDto){
        Book item = (Book)itemRepository.findOne(itemUpdateDto.getId());
        item.setName(itemUpdateDto.getName());
        item.setPrice(itemUpdateDto.getPrice());
        item.setStockQuantity(itemUpdateDto.getStockQuantity());
        item.setAuthor(itemUpdateDto.getAuthor());
        item.setIsbn(itemUpdateDto.getIsbn());
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
