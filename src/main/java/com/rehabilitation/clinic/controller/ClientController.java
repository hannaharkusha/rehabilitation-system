package com.rehabilitation.clinic.controller;

import com.rehabilitation.clinic.entity.Client;
import com.rehabilitation.clinic.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/edit")
    public void editClient(int clientId, String name, String surname, String email, String pesel, String phoneNr, String address) {
        clientService.editClient(clientId,name,surname,email,pesel,phoneNr,address);
    }
    @PutMapping("/edit-address")
    public void editClientAddress(int clientId, String address ) {
        clientService.editClientAddress( clientId, address);
    }
    @PutMapping("/edit-phoneNr")
    public void editClientPhoneNr(int clientId, String phoneNr ) {
        clientService.editClientPhoneNr( clientId, phoneNr);
    }
    @PutMapping("/edit-pesel")
    public void editClientPesel(int clientId, String pesel ) {
        clientService.editClientPesel( clientId, pesel);
    }
    @PutMapping("/edit-name")
    public void editClientName(int clientId, String name ) {
        clientService.editClientName( clientId, name);
    }
    @PutMapping("/edit-surname")
    public void editClientSurname(int clientId, String surname ) {
        clientService.editClientSurname( clientId, surname);
    }
    @PutMapping("/edit-email")
    public void editClientEmail(int clientId, String email ) {
        clientService.editClientEmail( clientId, email);
    }

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
