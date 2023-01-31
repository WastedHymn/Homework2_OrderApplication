package org.patika.dto;

import java.time.LocalDate;
import java.util.List;

public class NewInvoiceDto {
    private Long clientId;
    private float totalAmount;
    private LocalDate recordDate;
    private Long companyId;
    private List<Long> itemIdList;

    public NewInvoiceDto() {
    }

    public NewInvoiceDto(Long clientId, LocalDate recordDate, Long companyId, List<Long> itemIdList) {
        this.clientId = clientId;
        this.recordDate = recordDate;
        this.companyId = companyId;
        this.itemIdList = itemIdList;
    }

    public NewInvoiceDto(Long clientId, LocalDate recordDate, List<Long> itemIdList) {
        this.clientId = clientId;
        this.recordDate = recordDate;
        this.itemIdList = itemIdList;
    }

    public NewInvoiceDto(Long clientId, float totalAmount, LocalDate recordDate, Long companyId, List<Long> itemIdList) {
        this.clientId = clientId;
        this.totalAmount = totalAmount;
        this.recordDate = recordDate;
        this.companyId = companyId;
        this.itemIdList = itemIdList;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<Long> getItemList() {
        return itemIdList;
    }

    public void setItemList(List<Long> itemIdList) {
        this.itemIdList = itemIdList;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public List<Long> getItemIdList() {
        return itemIdList;
    }

    public void setItemIdList(List<Long> itemIdList) {
        this.itemIdList = itemIdList;
    }
}
