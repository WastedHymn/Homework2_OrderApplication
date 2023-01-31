package org.patika.application;

import org.patika.repository.ClientRepository;
import org.patika.repository.CompanyRepository;
import org.patika.repository.InvoiceRepository;
import org.patika.repository.ItemRepository;
import org.patika.service.ClientService;
import org.patika.service.CompanyService;
import org.patika.service.InvoiceService;
import org.patika.service.ItemService;

public class RequiredObjects {
    private static RequiredObjects instance;

    private ClientRepository clientRepository;
    private InvoiceRepository invoiceRepository;
    private ItemRepository itemRepository;
    private CompanyRepository companyRepository;

    private ItemService itemService;
    private InvoiceService invoiceService;
    private ClientService clientService;
    private CompanyService companyService;

    private ApplicationExampleData applicationExampleData;
    private OrderApplication orderApplication;
    private ApplicationQueries applicationQueries;

    private RequiredObjects() {
        initObjects();
        injectDependencies();
    }

    //Initialize objects
    private void initObjects() {
        applicationExampleData = new ApplicationExampleData();

        this.clientRepository = new ClientRepository();
        this.invoiceRepository = new InvoiceRepository();
        this.itemRepository = new ItemRepository();
        this.companyRepository = new CompanyRepository();

        this.clientService = new ClientService(clientRepository);
        this.invoiceService = new InvoiceService(invoiceRepository);
        this.itemService = new ItemService(itemRepository);
        this.companyService = new CompanyService(companyRepository);
        this.applicationQueries = new ApplicationQueries();

        orderApplication = new OrderApplication(
                itemService,
                invoiceService,
                clientService,
                companyService,
                applicationQueries
        );
    }

    private void injectDependencies() {
        clientService.setInvoiceService(invoiceService);
        invoiceService.setItemService(itemService);
        invoiceService.setClientService(clientService);
        invoiceService.setCompanyService(companyService);
    }

    //Apply singleton pattern
    public static RequiredObjects getInstance() {
        if (instance == null) {
            instance = new RequiredObjects();
        }
        return instance;
    }

    public ItemService getItemService() {
        return itemService;
    }

    public InvoiceService getInvoiceService() {
        return invoiceService;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public CompanyService getCompanyService() {
        return companyService;
    }

    public ApplicationExampleData getExampleDatas() {
        return applicationExampleData;
    }

    public OrderApplication getOrderApplication() {
        return orderApplication;
    }
}
