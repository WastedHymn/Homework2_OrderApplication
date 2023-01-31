package org.patika.application;

import org.patika.dto.NewCompanyDto;
import org.patika.dto.NewInvoiceDto;
import org.patika.dto.NewItemDto;
import org.patika.dto.NewClientDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApplicationExampleDatas {
    List<NewClientDto> exampleClients = new ArrayList<>();
    List<NewItemDto> exampleItems = new ArrayList<>();
    List<NewInvoiceDto> exampleInvoices = new ArrayList<>();
    List<NewCompanyDto> exampleCompanies = new ArrayList<>();

    public ApplicationExampleDatas() {
        addExampleClientSignupDtos();
        addExampleNewItemDtos();
        addExampleNewInvoiceDtos();
        addExampleCompanies();
    }

    private void addExampleClientSignupDtos() {
        NewClientDto client1 = new NewClientDto(
                "Alivia",
                "Nixon",
                LocalDate.of(2015, 1, 13)
        );
        NewClientDto client2 = new NewClientDto(
                "Hanna",
                "Hartman",
                LocalDate.of(2014, 4, 4)
        );
        NewClientDto client3 = new NewClientDto(
                "Cole",
                "Fox",
                LocalDate.of(2012, 6, 18)
        );
        NewClientDto client4 = new NewClientDto(
                "Carol",
                "Torres",
                LocalDate.of(2010, 6, 3)
        );
        NewClientDto client5 = new NewClientDto(
                "Dennis",
                "Sanders",
                LocalDate.of(2008, 4, 17)
        );
        exampleClients.addAll(Arrays.asList(client1, client2, client3, client4, client5));
    }

    private void addExampleCompanies() {
        NewCompanyDto company1 = new NewCompanyDto("Siemens", "Electronics");
        NewCompanyDto company2 = new NewCompanyDto("Dell", "IT");
        NewCompanyDto company3 = new NewCompanyDto("Bayer", "Health");
        NewCompanyDto company4 = new NewCompanyDto("Nestle", "Food");
        NewCompanyDto company5 = new NewCompanyDto("Udemy", "Education");
        exampleCompanies.addAll(List.of(company1, company2, company3, company4, company5));
    }

    private void addExampleNewItemDtos() {
        NewItemDto item1 = new NewItemDto("item_1", 10);
        NewItemDto item2 = new NewItemDto("item_2", 25);
        NewItemDto item3 = new NewItemDto("item_3", 50);
        NewItemDto item4 = new NewItemDto("item_4", 100);
        NewItemDto item5 = new NewItemDto("item_5", 250);
        NewItemDto item6 = new NewItemDto("item_6", 500);
        exampleItems.addAll(Arrays.asList(item1, item2, item3, item4, item5, item6));
    }

    private void addExampleNewInvoiceDtos() {
        List<Long> itemList_1 = new ArrayList<>(List.of(1L, 3L, 5L));//310
        List<Long> itemList_2 = new ArrayList<>(List.of(6L, 6L, 3L, 3L, 4L, 4L, 4L, 4L, 1L));//1510
        List<Long> itemList_3 = new ArrayList<>(List.of(6L, 6L, 3L, 3L, 4L, 4L, 4L, 2L, 3L, 4L, 1L, 4L));//1685
        List<Long> itemList_4 = new ArrayList<>(List.of(1L, 2L, 3L));//85

        NewInvoiceDto invoice1 = new NewInvoiceDto(1L, LocalDate.of(2020, 6, 7), 1L, itemList_1);
        NewInvoiceDto invoice2 = new NewInvoiceDto(1L, LocalDate.of(2020, 3, 1), 5L, itemList_2);

        NewInvoiceDto invoice3 = new NewInvoiceDto(3L, LocalDate.of(2020, 3, 4), 4L, itemList_2);
        NewInvoiceDto invoice4 = new NewInvoiceDto(3L, LocalDate.of(2020, 6, 7), 3L, itemList_1);

        NewInvoiceDto invoice5 = new NewInvoiceDto(4L, LocalDate.of(2021, 6, 14), 5L, itemList_3);

        NewInvoiceDto invoice6 = new NewInvoiceDto(2L, LocalDate.of(2018, 6, 16), 2L, itemList_1);
        NewInvoiceDto invoice7 = new NewInvoiceDto(2L, LocalDate.of(2029, 6, 23), 4L, itemList_2);

        NewInvoiceDto invoice8 = new NewInvoiceDto(2L, LocalDate.of(2015, 6, 27), 2L, itemList_4);
        NewInvoiceDto invoice9 = new NewInvoiceDto(2L, LocalDate.of(2017, 6, 3), 1L, itemList_4);

        exampleInvoices.addAll(Arrays.asList(
                invoice1,
                invoice2,
                invoice3,
                invoice4,
                invoice5,
                invoice6,
                invoice7,
                invoice8,
                invoice9
        ));
    }

    public List<NewClientDto> getExampleClients() {
        return exampleClients;
    }

    public List<NewItemDto> getExampleItems() {
        return exampleItems;
    }

    public List<NewInvoiceDto> getExampleInvoices() {
        return exampleInvoices;
    }

    public List<NewCompanyDto> getExampleCompanies() {
        return exampleCompanies;
    }
}
