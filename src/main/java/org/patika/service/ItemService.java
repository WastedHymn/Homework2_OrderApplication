package org.patika.service;


import org.patika.dto.NewItemDto;
import org.patika.entity.Item;
import org.patika.repository.ItemRepository;

import java.util.List;

public class ItemService implements Service{
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void addItem(NewItemDto newItemDto) {
        boolean doesItemExist = itemRepository.checkItem(newItemDto.getItemName());
        if (doesItemExist) {
            System.out.println("Item already exists in the database.");
        } else {
            Item tempItem = new Item(ItemRepository.getIdCounter(), newItemDto.getItemName(), newItemDto.getItemPrice());
            itemRepository.addItem(tempItem);
            System.out.println(tempItem + " added.");
        }
    }

    @Override
    public List<Item> getAll() {
        return itemRepository.getAll();
    }

    @Override
    public Item getById(Long id) {
        return itemRepository.getById(id);
    }

    public float calculateTotalAmount(List<Long> itemIdList){
        float totalAmount = 0;
        for (Long itemId : itemIdList) {
            totalAmount += itemRepository.getById(itemId).getItemPrice();
        }
        return totalAmount;
    }
}
