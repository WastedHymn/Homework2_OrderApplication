package org.patika.repository;

import org.patika.entity.Item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ItemRepository implements Repository {
    private final static List<Item> itemList = new ArrayList<>();
    private final static HashSet<String> itemInfos = new HashSet<>();
    private static Long idCounter = 1L;
    @Override
    public List<Item> getAll() {
        return itemList;
    }

    @Override
    public Item getById(Long id) {
        return itemList.stream()
                .filter(item -> item.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    public void addItem(Item item){
        itemList.add(item);
        itemInfos.add(item.getItemName() + " " + item.getItemPrice());
        idCounter += 1;
    }

    public static Long getIdCounter() {
        return idCounter;
    }

    public boolean checkItem(String itemInfo){
        return itemInfos.contains(itemInfo);
    }
}
