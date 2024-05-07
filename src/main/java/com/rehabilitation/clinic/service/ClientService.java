package com.rehabilitation.clinic.service;

import com.rehabilitation.clinic.entity.Client;
import com.rehabilitation.clinic.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(int id) {
        try {
            if(id <= 0) {
                throw new IllegalArgumentException("ClientService: incorrect id");
            }
            return clientRepository.findById(id);
        } catch (Exception e) {
            System.err.println("Error retrieving client: " + e.getMessage());
            throw e;
        }
    }

    public Optional<Client> getClientByEmail(String email) {
        try {
            if (email == null) {
                throw new IllegalArgumentException("ClientService: incorrect email");
            }
            return clientRepository.findByEmail(email);
        } catch (Exception e) {
            System.err.println("Error retrieving client by email: " + e.getMessage());
            throw e;
        }
    }

    public void addClient(String name, String surname, String password, String email, String pesel, String phoneNr, String address) {
        try {
            if(name == null || surname == null || password == null || email == null) {
                throw new IllegalArgumentException("ClientService: incorrect data");
            }
            Client client = new Client(name, surname, password, email,pesel, phoneNr, address);
            clientRepository.save(client);
        } catch (Exception e) {
            System.err.println("Error adding client: " + e.getMessage());
            throw e;
        }
    }

    public void deleteClientById(int id) {
        try {
            if(id <= 0) {
                throw new IllegalArgumentException("ClientService: incorrect id");
            }
            clientRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println("Error deleting client: " + e.getMessage());
        }
    }

}
