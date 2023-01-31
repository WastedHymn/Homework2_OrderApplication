package org.patika.application;

import org.patika.entity.Client;
import org.patika.entity.Invoice;
import org.patika.service.ClientService;
import org.patika.service.InvoiceService;
import org.patika.utils.DebugUtils;
import org.patika.utils.MenuInputUtils;
import org.patika.utils.MenuPrintUtils;

import java.util.List;

public class ApplicationQueries {
    //List all the clients.
    public void listClients(List<Client> clients) {
        DebugUtils.printListElements(clients);
    }

    //List all the invoices.
    public void listInvoices(List<Invoice> invoices) {
        DebugUtils.printListElements(invoices);
    }

    //List specified client's invoices.
    public void listClientInvoices(Long clientId, InvoiceService invoiceService) {
        DebugUtils.printListElements(invoiceService.getInvoicesByClientId(clientId));
    }

    //Search all clients by a letter.
    public void searchClientsByALetter(ClientService clientService) {
        System.out.print("Please enter a letter: ");
        String letterToSearch = MenuInputUtils.getStringInput();
        DebugUtils.printListElements(clientService.findClientsByLetter(letterToSearch));
    }

    //Search all clients by client signup month
    public void getUserInvoicesByClientRegistrationMonth(InvoiceService invoiceService) {
        System.out.print("Please enter a number(1-12): ");
        int month = MenuInputUtils.getIntegerInput();
        if (month >= 1 && month <= 12) {
            DebugUtils.printClientInvoiceMap(invoiceService.getClientInvoicesByClientSignupMonth(month));
            //System.out.println("TOTAL: " + invoiceService.getTotalAmountOfInvoiceAmountsByClientSignupMonth(month));
            return;
        }
        MenuInputUtils.warnClientAboutInputValues();
    }

    //Show the clients names whose invoices' invoice amount is less than the given invoice amount.
    public void listClientNamesByInvoiceTotalAmountLessThan(ClientService clientService) {
        System.out.print("Please enter an amount: ");
        float lessAmount = MenuInputUtils.getFloatInput();

        if (lessAmount >= 0) {
            DebugUtils.printListElements(clientService.findClientNamesByInvoiceTotalAmountLessThan(lessAmount));
            return;
        }
        MenuInputUtils.warnClientAboutInputValues();
    }

    //List the invoices that have a bigger invoice amount than the given invoice amount.
    public void listInvoicesByTotalAmountBiggerThan(InvoiceService invoiceService) {
        System.out.print("Please enter an amount: ");
        float biggerThanAmount = MenuInputUtils.getFloatInput();

        if (biggerThanAmount >= 0) {
            DebugUtils.printListElements(invoiceService.getInvoicesByTotalAmountBiggerThan(biggerThanAmount));
            return;
        }
        MenuInputUtils.warnClientAboutInputValues();
    }

    //Calculate the average invoice amount of the invoices that have a bigger invoice amount than the given invoice amount.
    public void getAverageInvoiceAmountByGivenInvoiceAmount(InvoiceService invoiceService) {
        System.out.print("Please enter an amount: ");
        float amount = MenuInputUtils.getFloatInput();

        if (amount >= 0) {
            List<Invoice> invoices = invoiceService.getInvoicesByTotalAmountBiggerThan(amount);
            float avg = invoiceService.getAverageTotalAmountByInvoiceAmountBiggerThan(invoices);
            if (avg >= 0) {
                DebugUtils.printListElements(invoices);
                System.out.println("Average: " + avg);
            }
            return;
        }
        MenuInputUtils.warnClientAboutInputValues();
    }

    //8.
    public void couldntFindAFuncName(InvoiceService invoiceService) {
        System.out.print("Enter a month(1-12): ");
        int month = MenuInputUtils.getIntegerInput();
        System.out.print("Enter an amount: ");
        float amount = MenuInputUtils.getFloatInput();

        if (month >= 1 && month <= 12 && amount >= 0) {
            MenuPrintUtils.printCompaniesAndSectors(invoiceService.couldntFindAFuncName(month, amount));
            return;
        }
        MenuInputUtils.warnClientAboutInputValues();
    }
}
