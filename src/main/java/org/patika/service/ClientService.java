package org.patika.service;

import org.patika.dto.NewClientDto;
import org.patika.entity.Invoice;
import org.patika.entity.Client;
import org.patika.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientService implements Service {
    private final ClientRepository clientRepository;
    private InvoiceService invoiceService;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    @Override
    public Client getById(Long id) {
        return clientRepository.getById(id);
    }

    public void addClient(NewClientDto newClientDto) {
        String info = newClientDto.getName() + " " + newClientDto.getLastName();
        boolean doesClientExist = clientRepository.checkClient(info);

        if (doesClientExist) {
            System.out.println("Client already signed up.");
        } else {
            Client tempClient = new Client(
                    ClientRepository.getIdCounter(),
                    newClientDto.getName(),
                    newClientDto.getLastName(),
                    newClientDto.getRecordDate()
            );
            clientRepository.addClient(tempClient);
            clientRepository.addClientInfo(info);
            System.out.println(tempClient + " added.");
        }
    }

    public List<Client> findClientsByLetter(String letter) {
        return clientRepository.findClientsByLetter(letter);
    }

    public List<String> findClientNamesByInvoiceTotalAmountLessThan(float amount) {
        List<Long> clientIds = invoiceService.getInvoicesByTotalAmountLessThan(amount)
                .stream()
                .map(Invoice::getClientId).toList();
        //System.out.println("FOUND CLIENT IDS: " + clientIds);
        List<String> clientNames = new ArrayList<>();
        for (Long clientId : clientIds) {
            String cName = clientRepository.getById(clientId).getName();
            if (!clientNames.contains(cName))
                clientNames.add(cName);
        }
        return clientNames;
    }

    public List<Client> findClientsBySignupMonth(int month) {
        return clientRepository.getAll()
                .stream()
                .filter(client -> client.getRegistrationDate().getMonth().getValue() == month)
                //.map(Client::getId)
                .collect(Collectors.toList());
    }

    public List<Long> findClientIdsBySignupMonth(int month){
        return clientRepository.getAll()
                .stream()
                .filter(client -> client.getRegistrationDate().getMonth().getValue() == month)
                .map(Client::getId)
                .collect(Collectors.toList());
    }

}
