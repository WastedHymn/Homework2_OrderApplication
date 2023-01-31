package org.patika.service;

import org.patika.dto.NewInvoiceDto;
import org.patika.entity.Client;
import org.patika.entity.Company;
import org.patika.entity.Invoice;
import org.patika.repository.InvoiceRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InvoiceService implements Service {
    private final InvoiceRepository invoiceRepository;
    private ItemService itemService;
    private ClientService clientService;
    private CompanyService companyService;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    public void addInvoice(NewInvoiceDto newInvoice) {
        Invoice tempInvoice = new Invoice(
                InvoiceRepository.getIdCounter(),
                itemService.calculateTotalAmount(newInvoice.getItemList()),
                newInvoice.getRecordDate(),
                newInvoice.getClientId(),
                newInvoice.getCompanyId(),
                newInvoice.getItemList()
        );
        invoiceRepository.addInvoice(tempInvoice);
        System.out.println(tempInvoice + " added to the database.");
    }

    @Override
    public List<Invoice> getAll() {
        return invoiceRepository.getAll();
    }

    @Override
    public Invoice getById(Long id) {
        return invoiceRepository.getById(id);
    }

    //Get companies whose average total invoice in a specified month is less than the specified amount.
    public List<Company> couldntFindAFuncName(int month, float amount){
        //GET ALL COMPANIES
        List<Company> companies = companyService.getAll();
        List<Company> filteredCompanies = new ArrayList<>();
        //LOOP THROUGH ALL COMPANIES TO FIND INVOICES IN THE SPECIFIED MONTH
        for (Company company : companies) {
            List<Invoice> clientInvoices = invoiceRepository.getByCompanyIdAndMonth(company.getId(), month);
            Optional<Float> totalAmount = clientInvoices.stream()
                    .map(Invoice::getTotalAmount)
                    .reduce(Float::sum);
            if(totalAmount.isPresent()){
                float average = totalAmount.get() / clientInvoices.size();
                if(average < amount){
                    if(!filteredCompanies.contains(company)){
                        filteredCompanies.add(company);
                    }
                }
            }
        }
        return filteredCompanies;
    }

    public List<Invoice> getInvoicesByClientId(Long clientId){
        return invoiceRepository.getByClientId(clientId);
    }
    public List<Invoice> getInvoicesByTotalAmountBiggerThan(float amount) {
        return invoiceRepository.getAll().stream()
                .filter(invoice -> invoice.getTotalAmount() > amount)
                .collect(Collectors.toList());
    }

    public List<Invoice> getInvoicesByTotalAmountLessThan(float amount) {
        return invoiceRepository.getAll().stream()
                .filter(invoice -> invoice.getTotalAmount() < amount)
                .collect(Collectors.toList());
    }

    public float getAverageTotalAmountByInvoiceAmountBiggerThan(List<Invoice> invoices) {
        //List<Invoice> invoices = getInvoicesByTotalAmountBiggerThan(amount);
        Optional<Float> total = invoices
                .stream()
                .map(Invoice::getTotalAmount)
                .reduce(Float::sum);
        return total.map(aFloat -> aFloat / invoices.size()).orElse(-1F);
    }

    public HashMap<Client, List<Invoice>> getClientInvoicesByClientSignupMonth(int month) {
        HashMap<Client, List<Invoice>> clientListHashMap = new HashMap<>();
        List<Client> clients = clientService.findClientsBySignupMonth(month);
        List<List<Invoice>> invoices = new ArrayList<>();
        for (Client client : clients) {
            clientListHashMap.put(client, invoiceRepository.getByClientId(client.getId()));
        }
        return clientListHashMap;
    }

    public float getTotalAmountOfInvoiceAmountsByClientSignupMonth(int month) {
        float totalAmount = 0;
        List<Long> clientIds = clientService.findClientIdsBySignupMonth(month);
        for (Long clientId : clientIds) {
            for (Invoice invoice : invoiceRepository.getByClientId(clientId)) {
                //System.out.println("CLIENT ID: " + clientId + " INVOICE: " + invoice);
                totalAmount += invoice.getTotalAmount();
            }
        }
        return totalAmount;
    }
}
