package org.patika.repository;

import org.patika.entity.Invoice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceRepository implements Repository {

    private final static List<Invoice> invoices = new ArrayList<>();
    private static Long idCounter = 1L;

    //GET ALL INVOICES
    @Override
    public List<Invoice> getAll() {
        return invoices;
    }

    //GET INVOICE BY ID
    @Override
    public Invoice getById(Long id) {
        return invoices.stream()
                .filter(invoice -> invoice.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    public List<Invoice> getByCompanyIdAndMonth(Long companyId, int month){
         return invoices.stream()
                .filter(invoice -> invoice.getCompanyId().equals(companyId))
                .filter(invoice -> invoice.getRecordDate().getMonth().getValue() == month)
                .collect(Collectors.toList());
    }

    public List<Invoice> getByClientId(Long clientId){
        return invoices.stream()
                .filter(invoice -> invoice.getClientId().equals(clientId))
                .collect(Collectors.toList());
    }
    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
        idCounter += 1;
    }

    //GET INVOICES BY TOTAL AMOUNT GRATER THAN "AMOUNT"
    public List<Invoice> getByGraterThan(float amount) {
        return invoices.stream()
                .filter(invoice -> invoice.getTotalAmount() > amount)
                .collect(Collectors.toList());
    }

    //GET AVERAGE TOTAL AMOUNT BY TOTAL AMOUNT GRATER THAN "AMOUNT"
    public float getAverageAmountByGraterThan(float amount) {
        List<Invoice> invoiceList = getByGraterThan(amount);
        return invoiceList.stream()
                .map(Invoice::getTotalAmount)
                .reduce((float) 0, Float::sum);
    }

    //GET INVOICES BY TOTAL AMOUNT LESS THAN "AMOUNT"
    public List<Invoice> getByLessThen(float amount) {
        return invoices.stream()
                .filter(invoice -> invoice.getTotalAmount() < amount)
                .collect(Collectors.toList());
    }

    public static Long getIdCounter() {
        return idCounter;
    }
}
