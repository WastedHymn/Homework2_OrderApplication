package org.patika.application;

import org.patika.entity.Client;
import org.patika.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class LoggedClient {
    private Client currentClient;
    private Long companyId = null;
    private List<Item> cart = new ArrayList<>();
    private float totalCartAmount = 0;
    public LoggedClient() {
    }

    public LoggedClient(Client currentClient) {
        this.currentClient = currentClient;
    }
    public void addItemToCart(Item item){
        cart.add(item);
        totalCartAmount += item.getItemPrice();
    }
    public void removeAllItemsFromCart(){
        cart.clear();
        totalCartAmount = 0;
    }
    public float getTotalCartAmount() {
        return totalCartAmount;
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public List<Item> getCart() {
        return cart;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
    }
}
