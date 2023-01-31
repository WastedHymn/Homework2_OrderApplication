package org.patika.application;

import org.patika.dto.NewClientDto;
import org.patika.dto.NewInvoiceDto;
import org.patika.entity.Client;
import org.patika.entity.Company;
import org.patika.entity.Item;
import org.patika.service.CompanyService;
import org.patika.service.InvoiceService;
import org.patika.service.ItemService;
import org.patika.service.ClientService;
import org.patika.utils.MenuInputUtils;
import org.patika.utils.MenuPrintUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class OrderApplication {
    private final ItemService itemService;
    private final InvoiceService invoiceService;
    private final ClientService clientService;
    private final CompanyService companyService;
    private final ApplicationQueries applicationQueries;
    private LoggedClient loggedClient = null;

    public OrderApplication(ItemService itemService, InvoiceService invoiceService, ClientService clientService, CompanyService companyService, ApplicationQueries applicationQueries) {
        this.itemService = itemService;
        this.invoiceService = invoiceService;
        this.clientService = clientService;
        this.companyService = companyService;
        this.applicationQueries = applicationQueries;
    }

    //Set current client.
    public void setLoggedClient(LoggedClient loggedClient) {
        this.loggedClient = loggedClient;
    }

    //List companies and get a company id.
    public int selectCompany() {
        List<Company> companyList = companyService.getAll();
        MenuPrintUtils.printCompanies(companyList);
        int companySelection = MenuInputUtils.getIntegerInput();
        if (companySelection >= 0 && companySelection <= companyList.size())
            return companySelection;
        return -1;
    }

    //Choose a client.
    public int chooseAClient() {
        int option = MenuInputUtils.getIntegerInput();
        //Return main menu
        if (option == 0)
            return 0;
        Client foundClient = clientService.getById(Long.valueOf(option));
        //Login client
        if (foundClient != null) {
            setLoggedClient(new LoggedClient(foundClient));
        } else {
            System.out.println("Client couldn't found.");
        }
        return option;
    }

    //List client names and get a client id. Set current client id.
    public int clientLoginPage() {
        MenuPrintUtils.printClientInfos(clientService.getAll());
        return chooseAClient();
    }

    //List client options
    public int clientOptionsPage() {
        MenuPrintUtils.printClientOptions(
                loggedClient.getCurrentClient().getName(),
                loggedClient.getCurrentClient().getLastName()
        );
        return MenuInputUtils.getIntegerInput();
    }

    public void logOut() {
        setLoggedClient(null);
        System.out.println("Signed Out.");
    }

    public void checkClientMenuSelection(int selection) {
        switch (selection) {
            case 1 -> shoppingMenu();
            case 2 ->
                    applicationQueries.listClientInvoices(loggedClient.getCurrentClient().getId(), invoiceService);//listClientInvoices(loggedClient.getCurrentClient().getId());
            case 3 -> logOut();
            default -> MenuInputUtils.warnClientAboutInputValues();
        }
    }

    public void checkMainMenuSelection(int selection) {
        switch (selection) {
            case 1 -> applicationQueries.listInvoices(invoiceService.getAll());//listInvoices();
            case 2 -> applicationQueries.listClients(clientService.getAll());//listClients();
            case 3 -> applicationQueries.searchClientsByALetter(clientService);//searchClientsByALetter();
            case 4 ->
                    applicationQueries.getUserInvoicesByClientRegistrationMonth(invoiceService);//getUserInvoicesByClientRegistrationMonth();
            case 5 ->
                    applicationQueries.listClientNamesByInvoiceTotalAmountLessThan(clientService);//listClientNamesByInvoiceTotalAmountLessThan();
            case 6 ->
                    applicationQueries.listInvoicesByTotalAmountBiggerThan(invoiceService);//listInvoicesByTotalAmountBiggerThan();
            case 7 ->
                    applicationQueries.getAverageInvoiceAmountByGivenInvoiceAmount(invoiceService);//getAverageInvoiceAmountByGivenInvoiceAmount();
            case 8 -> applicationQueries.couldntFindAFuncName(invoiceService);//couldntFindAFuncName();
            case 9 -> clientMenu();
            case 10 -> createNewClient();
            case 11 -> System.exit(0);
            default -> System.out.println("Please press a valid option number.");
        }
        MenuInputUtils.pressToContinue();
    }

    public void createNewClient() {
        //Get client info
        System.out.print("Client's first name: ");
        String firstName = MenuInputUtils.getStringInput();
        System.out.print("Client's last name: ");
        String lastName = MenuInputUtils.getStringInput();
        //Create random date
        Random random = new Random();
        int year = random.nextInt(2016 - 2008) + 2008;
        int month = random.nextInt(13 - 1) + 1;
        int day = random.nextInt(29 - 1) + 1;
        LocalDate registrationDate = LocalDate.of(year, month, day);
        NewClientDto newClientDto = new NewClientDto(firstName, lastName, registrationDate);
        clientService.addClient(newClientDto);
    }

    public void createInvoiceForPurchase() {
        List<Long> itemIds = loggedClient.getCart()
                .stream()
                .map(Item::getId)
                .toList();
        Random random = new Random();
        int day = random.nextInt(29 - 1) + 1;
        int month = random.nextInt(13 - 1) + 1;
        NewInvoiceDto newInvoiceDto = new NewInvoiceDto(
                loggedClient.getCurrentClient().getId(),
                loggedClient.getTotalCartAmount(),
                LocalDate.of(2022, month, day),
                loggedClient.getCompanyId(),
                itemIds
        );
        invoiceService.addInvoice(newInvoiceDto);
    }

    //Reset the client's cart and selected company id.
    public void resetClientCartAndSelectedCompany() {
        loggedClient.setCompanyId(null);
        loggedClient.removeAllItemsFromCart();
    }

    //Item Menu Loop
    public void itemMenu() {
        //Get all the items
        List<Item> items = itemService.getAll();
        //Show all the items
        MenuPrintUtils.printItemInfos(
                items,
                companyService.getCompanyNameByCompanyId(loggedClient.getCompanyId())
        );
        //Input Loop
        while (true) {
            System.out.print("->");
            int clientChoice = MenuInputUtils.getIntegerInput();
            //Return to the client menu
            if (clientChoice == 0) {
                resetClientCartAndSelectedCompany();
                break;
            } else if (clientChoice == -1) {//Show cart
                if (loggedClient.getCart().size() > 0) {
                    MenuPrintUtils.printClientCart(loggedClient.getCart(), loggedClient.getTotalCartAmount());
                } else {
                    System.out.println("Your cart is empty.");
                }
            } else if (clientChoice == -2) {//Remove all items from the cart
                loggedClient.removeAllItemsFromCart();
                System.out.println("All of the items in your cart has been removed.");
            } else if (clientChoice == -3) {//Process checkout.
                createInvoiceForPurchase();
                System.out.println("Purchase is successful. Returning to the company menu.");
                resetClientCartAndSelectedCompany();
                break;
            } else {
                //Check for the item id.
                if (clientChoice >= 1 && clientChoice <= items.size()) {
                    Item cartItem = itemService.getById(Long.valueOf(clientChoice));
                    //Add item to the cart.
                    if (cartItem != null) {
                        loggedClient.addItemToCart(cartItem);
                        System.out.println(cartItem.getItemName() + " added to your cart.");
                    }
                } else {
                    MenuInputUtils.warnClientAboutInputValues();
                }
            }


        }
    }

    //Shopping Menu Loop
    public void shoppingMenu() {
        while (true) {
            int companySelection = selectCompany();
            if (companySelection == 0) {
                break;
            } else if (companySelection < 0) {
                System.out.println("Couldn't find a company.");
            } else if (loggedClient.getCompanyId() == null) {
                loggedClient.setCompanyId(Long.valueOf(companySelection));
                itemMenu();
            }
        }
    }

    //Client Menu Loop
    public void clientMenu() {
        while (true) {
            //If a client has logged in than show client options
            if (loggedClient != null) {
                int option = clientOptionsPage();
                checkClientMenuSelection(option);
                if (option == 3) {
                    break;
                }
            } else {
                //Show all the clients and check if the user choose to exit
                int selection = clientLoginPage();
                if (selection == 0)
                    break;
            }
        }
    }

    //Get an option value.
    public void selectAMainMenuOption() {
        int number = MenuInputUtils.getIntegerInput();
        checkMainMenuSelection(number);
    }

    //Main Menu Loop
    public void MainMenu() {
        while (true) {
            //Print main menu options
            MenuPrintUtils.printMainMenuOptions();
            //Get an option input
            selectAMainMenuOption();
        }
    }
}
