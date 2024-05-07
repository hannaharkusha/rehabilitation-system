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


    //sprawdzic haslo do logowania

    @GetMapping("/email")
    public Optional<Client> getClientByEmail(String email) {
        return clientService.getClientByEmail(email);
    }

    //edycja danych osobowych

    @GetMapping("/add")
    public void addClient(String name, String surname, String password, String email, String pesel, String phoneNr, String address) {
        clientService.addClient(name, surname, password, email,pesel, phoneNr, address);
    }

    @GetMapping("/delete")
    public void deleteClientById(int id) {
        clientService.deleteClientById(id);
    }

    //zmiana hasla
}
