package org.patika.dto;

public class NewItemDto {
    private String itemName;
    private float itemPrice;

    public NewItemDto() {
    }

    public NewItemDto(String itemName, float itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }
}
