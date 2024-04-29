package com.rehabilitation.clinic.service;

import com.rehabilitation.clinic.entity.Client;
import com.rehabilitation.clinic.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
                throw new IllegalArgumentException("Client: incorrect id");
            }
            return clientRepository.findById(id);
        } catch (Exception e) {
            System.err.println("Error retrieving employees: " + e.getMessage());
            throw e;
        }
    }

    public void addClient(String name, String surname, String position, String password) {
        try {
            if(name == null || surname == null || position == null || password == null) {
                throw new IllegalArgumentException("Client: incorrect data");
            }
            Client client = new Client(name, surname, position, password);
            clientRepository.save(client);
        } catch (Exception e) {
            System.err.println("Error adding employee: " + e.getMessage());
            throw e;
        }
    }

    public void deleteClientById(int id) {
        try {
            if(id <= 0) {
                throw new IllegalArgumentException("Client: incorrect id");
            }
            clientRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println("Error deleting client: " + e.getMessage());
        }
    }

}
