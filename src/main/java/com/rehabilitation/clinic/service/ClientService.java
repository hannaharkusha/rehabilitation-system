package com.rehabilitation.clinic.service;

import com.rehabilitation.clinic.entity.Client;
import com.rehabilitation.clinic.repository.ClientRepository;
import encoding.PasswordEncoding;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;
    private final PasswordEncoding passwordEncoding;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        this.passwordEncoding = new PasswordEncoding();
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

    public Optional<Client> getClientByPesel(String pesel) {
        try {
            if (pesel == null) {
                throw new IllegalArgumentException("ClientService: incorrect email");
            }
            return clientRepository.findByPesel(pesel);
        } catch (Exception e) {
            System.err.println("Error retrieving client by email: " + e.getMessage());
            throw e;
        }
    }

    public void addClient(String name, String surname, String password, String email, String pesel, String phoneNr) {
        try {
            if(name == null || surname == null || password == null || email == null) {
                throw new IllegalArgumentException("ClientService: incorrect data");
            }

            String hashedPassword = passwordEncoding.hashPassword(password);
            Client client = new Client(name, surname, hashedPassword, email, pesel, phoneNr);
            clientRepository.save(client);
        } catch (Exception e) {
            System.err.println("Error adding client: " + e.getMessage());
            throw e;
        }
    }

    public void addClientWithoutAccount(String name, String surname, String pesel, String phoneNr) {
        try {
            if(name == null || surname == null) {
                throw new IllegalArgumentException("ClientService: incorrect data");
            }
            Client client = new Client(name, surname, pesel, phoneNr);
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


    public void editClient(int clientId, String name, String surname, String email, String pesel, String phoneNr) {
        try {
            if (clientId <= 0) {
                throw new IllegalArgumentException("ClientService: incorrect id");
            }

            Optional<Client> existingClientOptional = clientRepository.findById(clientId);
            if (existingClientOptional.isPresent()) {
                Client existingClient = existingClientOptional.get();
                existingClient.setName(name);
                existingClient.setSurname(surname);
                existingClient.setEmail(email);
                existingClient.setPesel(pesel);
                existingClient.setPhoneNr(phoneNr);

                clientRepository.save(existingClient);
            } else {
                throw new IllegalArgumentException("ClientService: Client not found with id " + clientId);
            }
        } catch (Exception e) {
            System.err.println("Error editing client: " + e.getMessage());
            throw e;
        }
    }

    public void editClientPhoneNr(int clientId, String phoneNr) {
        try {
            if (clientId <= 0) {
                throw new IllegalArgumentException("ClientService: incorrect id");
            }
            Optional<Client> existingClientOptional = clientRepository.findById(clientId);
            if (existingClientOptional.isPresent()) {
                Client existingClient = existingClientOptional.get();
                existingClient.setPhoneNr(phoneNr);

                clientRepository.save(existingClient);
            } else {
                throw new IllegalArgumentException("ClientService: Client not found with id " + clientId);
            }
        } catch (Exception e) {
            System.err.println("Error editing client: " + e.getMessage());
            throw e;
        }
    }

    public void editClientPesel(int clientId, String pesel) {
        try {
            if (clientId <= 0) {
                throw new IllegalArgumentException("ClientService: incorrect id");
            }
            Optional<Client> existingClientOptional = clientRepository.findById(clientId);
            if (existingClientOptional.isPresent()) {
                Client existingClient = existingClientOptional.get();
                existingClient.setPesel(pesel);

                clientRepository.save(existingClient);
            } else {
                throw new IllegalArgumentException("ClientService: Client not found with id " + clientId);
            }
        } catch (Exception e) {
            System.err.println("Error editing client: " + e.getMessage());
            throw e;
        }
    }

    public void editClientName(int clientId, String name) {
        try {
            if (clientId <= 0) {
                throw new IllegalArgumentException("ClientService: incorrect id");
            }
            Optional<Client> existingClientOptional = clientRepository.findById(clientId);
            if (existingClientOptional.isPresent()) {
                Client existingClient = existingClientOptional.get();
                existingClient.setName(name);

                clientRepository.save(existingClient);
            } else {
                throw new IllegalArgumentException("ClientService: Client not found with id " + clientId);
            }
        } catch (Exception e) {
            System.err.println("Error editing client: " + e.getMessage());
            throw e;
        }
    }

    public void editClientSurname(int clientId, String surname) {
        try {
            if (clientId <= 0) {
                throw new IllegalArgumentException("ClientService: incorrect id");
            }
            Optional<Client> existingClientOptional = clientRepository.findById(clientId);
            if (existingClientOptional.isPresent()) {
                Client existingClient = existingClientOptional.get();
                existingClient.setSurname(surname);

                clientRepository.save(existingClient);
            } else {
                throw new IllegalArgumentException("ClientService: Client not found with id " + clientId);
            }
        } catch (Exception e) {
            System.err.println("Error editing client: " + e.getMessage());
            throw e;
        }
    }

    public void editClientEmail(int clientId, String email) {
        try {
            if (clientId <= 0) {
                throw new IllegalArgumentException("ClientService: incorrect id");
            }
            Optional<Client> existingClientOptional = clientRepository.findById(clientId);
            if (existingClientOptional.isPresent()) {
                Client existingClient = existingClientOptional.get();
                existingClient.setEmail(email);

                clientRepository.save(existingClient);
            } else {
                throw new IllegalArgumentException("ClientService: Client not found with id " + clientId);
            }
        } catch (Exception e) {
            System.err.println("Error editing client: " + e.getMessage());
            throw e;
        }
    }

    public void editClientPassword(String email, String password){
        try {
            if (email == null) {
                throw new IllegalArgumentException("ClientService: incorrect id");
            }
            Optional<Client> existingClientOptional = clientRepository.findByEmail(email);
            if (existingClientOptional.isPresent()) {
                Client existingClient = existingClientOptional.get();
                String hashedPassword = passwordEncoding.hashPassword(password);
                existingClient.setPassword(hashedPassword);

                clientRepository.save(existingClient);
            } else {
                throw new IllegalArgumentException("ClientService: Client not found with id " + email);
            }
        } catch (Exception e) {
            System.err.println("Error editing client: " + e.getMessage());
            throw e;
        }
    }

    public Optional<Client> authenticateClient(String email, String password) {
        Optional<Client> optionalClient = clientRepository.findByEmail(email);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            if (passwordEncoding.checkPassword(password, client.getPassword())) {
                return Optional.of(client);
            }
        }
        return Optional.empty();
    }
}
