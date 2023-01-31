package org.patika.utils;

import org.patika.application.ApplicationExampleDatas;
import org.patika.dto.NewCompanyDto;
import org.patika.dto.NewInvoiceDto;
import org.patika.dto.NewItemDto;
import org.patika.dto.NewClientDto;
import org.patika.entity.Client;
import org.patika.entity.Invoice;
import org.patika.service.CompanyService;
import org.patika.service.InvoiceService;
import org.patika.service.ItemService;
import org.patika.service.ClientService;

import java.util.HashMap;
import java.util.List;

public class DebugUtils {
    public static void printListElements(List<?> elements) {
        for (Object element : elements) {
            System.out.println(element);
        }
    }

    public static void printInvoiceListInAList(List<List<Invoice>> listInAList) {
        for (List<Invoice> list : listInAList) {
            DebugUtils.printListElements(list);
        }
    }

    public static void printClientInvoiceMap(HashMap<Client, List<Invoice>> map) {
        map.forEach((client, invoices) -> {
            System.out.println(client);
            printListElements(invoices);
            //float total = invoices.stream().map(Invoice::getTotalAmount).reduce(Float::sum).get();
            System.out.println("========================================================");
        });
    }

    public static void addExampleDatas(ClientService clientService, ItemService itemService, InvoiceService invoiceService, CompanyService companyService, ApplicationExampleDatas applicationExampleDatas) {
        System.out.println("==================ADDING EXAMPLE DATA==================");
        //ADD EXAMPLE CLIENTS TO THE CLIENT DB
        for (NewClientDto exampleClient : applicationExampleDatas.getExampleClients()) {
            clientService.addClient(exampleClient);
        }
        System.out.println("========================================================");
        //ADD EXAMPLE COMPANIES TO THE COMPANY DB
        for (NewCompanyDto exampleCompany : applicationExampleDatas.getExampleCompanies()) {
            companyService.addCompany(exampleCompany);
        }
        System.out.println("========================================================");
        //ADD EXAMPLE ITEMS  TO THE ITEM DB
        for (NewItemDto exampleItem : applicationExampleDatas.getExampleItems()) {
            itemService.addItem(exampleItem);
        }
        System.out.println("========================================================");
        //ADD EXAMPLE INVOICES TO THE INVOICE DB
        for (NewInvoiceDto exampleNewInvoice : applicationExampleDatas.getExampleInvoices()) {
            invoiceService.addInvoice(exampleNewInvoice);
        }
    }
}
