package org.patika.entity;

import java.time.LocalDate;
import java.util.List;

public class Invoice {
    private Long id;
    private float totalAmount;
    private LocalDate recordDate;
    private Long clientId;
    private Long companyId;
    private List<Long> itemList;

    public Invoice() {
    }

    public Invoice(Long id, float totalAmount, LocalDate recordDate, Long clientId, Long sector, List<Long> itemList) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.recordDate = recordDate;
        this.clientId = clientId;
        this.companyId = sector;
        this.itemList = itemList;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getRecordDate() {
        return this.recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public Long getClientId() {
        return this.clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<Long> getItemList() {
        return itemList;
    }

    public void setItemList(List<Long> itemList) {
        this.itemList = itemList;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", totalAmount=" + totalAmount +
                ", recordDate=" + recordDate +
                ", clientId=" + clientId +
                ", companyId=" + companyId  +
                ", itemList=" + itemList +
                '}';
    }
}
