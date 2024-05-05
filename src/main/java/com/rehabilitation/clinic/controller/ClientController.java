package com.rehabilitation.clinic.controller;

import com.rehabilitation.clinic.entity.Client;
import com.rehabilitation.clinic.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/all")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/id")
    public Optional<Client> getClientById(int id) {
        return clientService.getClientById(id);
    }

    @GetMapping("/add")
    public void addClient(String name, String surname, String password) {
        clientService.addClient(name, surname, password);
    }

    @GetMapping("/delete")
    public void deleteClientById(int id) {
        clientService.deleteClientById(id);
    }
}
