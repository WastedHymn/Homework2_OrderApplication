package org.patika.repository;

import org.patika.entity.Client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class ClientRepository implements Repository {
    //STORE CLIENT INFOS INTO THE CLIENTINFOS HASHSET.
    private final static HashSet<String> clientInfos = new HashSet<>();
    //STORE CLIENT OBJECTS INTO THE CLIENTS LIST.
    private final static List<Client> clients = new ArrayList<>();
    private static Long idCounter = 1L;
    public ClientRepository() {
    }

    //GET ALL THE CLIENTS
    @Override
    public List<Client> getAll() {
        return clients;
    }

    //FIND CLIENT BY ID NUMBER
    @Override
    public Client getById(Long id) {
        return clients.stream()
                .filter(client -> client.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    //CHECK CLIENT INFO IF IT EXISTS
    public boolean checkClient(String clientInfo) {
        return clientInfos.contains(clientInfo);
    }

    //ADD NEW CLIENT
    public void addClient(Client client) {
        clients.add(client);
        idCounter += 1;
    }

    //ADD NEW CLIENT INFO
    public void addClientInfo(String clientInfo) {
        clientInfos.add(clientInfo);
    }

    //FIND CLIENTS BY LETTER
    public List<Client> findClientsByLetter(String letter) {
        return clients.stream()
                .filter(client -> client.getName().contains(letter))
                .collect(Collectors.toList());
    }

    public static Long getIdCounter() {
        return idCounter;
    }
}
