package org.patika.utils;

import org.patika.entity.Client;
import org.patika.entity.Company;
import org.patika.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class MenuPrintUtils {
    private static final List<String> mainMenuOptions = new ArrayList<>(List.of(
            "List all the invoices.",
            "List all the clients.",
            "Search all the clients by a letter and list them.",
            "Search all the clients by client registration month. List their invoices and show invoice amounts(1->January, 2->February etc.).",
            "Show the client's names whose invoices' invoice amount is less than the given invoice amount.",
            "List the invoices that have a bigger invoice amount than the specified invoice amount.",
            "Calculate the average invoice amount of the invoices that have a bigger invoice amount than the specified invoice amount.",
            "List the name and sector of companies whose average invoice amount for the specified month is less than the specified amount.",
            "Choose a client to login.",
            "Add a new client.",
            "Close the program."
    ));
    private static final List<String> clientMenuOptions = new ArrayList<>(List.of(
            "Choose a company and start to add items to your cart.",
            "List your invoices.",
            "Logout."
    ));

    private static void printTitle(String title) {
        System.out.println("==================" + title + "==================");
    }

    public static void printMainMenuOptions() {
        printTitle("MAIN MENU");
        for (int i = 0; i < mainMenuOptions.size(); i++)
            System.out.println((i + 1) + ". " + mainMenuOptions.get(i));
        System.out.print("-> ");
    }

    public static void printClientOptions(String clientName, String clientLastName) {
        printTitle("CLIENT MENU(" + clientName + " " + clientLastName + ")");
        for (int i = 0; i < clientMenuOptions.size(); i++)
            System.out.println((i + 1) + ". " + clientMenuOptions.get(i));
        System.out.print("->");
    }

    //List all the clients' info.
    public static void printClientInfos(List<Client> clients) {
        printTitle("LOGIN");
        for (Client client : clients) {
            System.out.println(client.getId() + ". " + client.getName() + " " + client.getLastName());
        }
        System.out.println("0. Return to the main menu.");
        System.out.print("->");
    }

    public static void printCompanies(List<Company> companies) {
        printTitle("SELECT A COMPANY");
        for (Company company : companies) {
            System.out.println(company.getId() + ". " + company.getCompanyName());
        }
        System.out.println("0. Return to the client menu.");
        System.out.print("->");
    }

    public static void printItemInfos(List<Item> items, String companyName) {
        printTitle("ITEMS(" + companyName + ")");
        System.out.println("* To add an item to your cart please enter an item number.");
        for (Item item : items) {
            System.out.println(item.getId() + ". " + item.getItemName() + ": " + item.getItemPrice());
        }
        System.out.println("0. Return to the client menu.");
        System.out.println("-1. Show cart.");
        System.out.println("-2. Remove all item from the cart.");
        System.out.println("-3. Checkout.");
    }

    public static void printClientCart(List<Item> cart, float totalValue) {
        printTitle("YOUR CART");
        for (Item item : cart) {
            System.out.println(item.getItemName() + " " + item.getItemPrice());
        }
        System.out.println("TOTAL: " + totalValue);
    }

    public static void printCompaniesAndSectors(List<Company> companies){
        for (Company company : companies) {
            System.out.println(company.getCompanyName() + " " + company.getSector());
        }
    }
}
