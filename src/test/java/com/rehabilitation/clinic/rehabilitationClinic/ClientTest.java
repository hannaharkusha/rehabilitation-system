package com.rehabilitation.clinic.rehabilitationClinic;

import com.rehabilitation.clinic.entity.Client;
import com.rehabilitation.clinic.repository.ClientRepository;
import com.rehabilitation.clinic.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ExtendWith({MockitoExtension.class})
public class ClientTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Autowired
    private ClientRepository realClientRepository;

    //unit tests to check the correctness of methods in the ClientService
//    @Test
//    public void testGetAllClients() {
//        List<Client> clients = Arrays.asList(
//                new Client("Jan", "Kowalski", "Client", "password"),
//                new Client("Anna", "Malinowska", "Client", "password")
//        );
//        clients.get(1).setUserId(1);
//        clients.get(2).setUserId(2);
//
//        when(clientRepository.findAll()).thenReturn(clients);
//        List<Client> result = clientService.getAllClients();
//        assertEquals(2, result.size());
//    }
//
//    @Test
//    public void testGetClientById() {
//        Client client = new Client("Jan", "Kowalski", "Client", "password");
//        client.setUserId(1);
//
//        when(clientRepository.findById(1)).thenReturn(Optional.of(client));
//        Optional<Client> result = clientService.getClientById(1);
//        assertTrue(result.isPresent());
//        assertEquals(client.getName(), result.get().getName());
//    }
//
//    @Test
//    public void testAddClient() {
//        Client client = new Client("Jan", "Kowalski", "Client", "password");
//        client.setUserId(1);
//
//        clientService.addClient(client.getName(), client.getSurname(), client.getPosition(), client.getPassword());
//        verify(clientRepository, times(1)).save(client);
//    }
//
//    @Test
//    public void testDeleteClientById() {
//        clientService.deleteClientById(1);
//        verify(clientRepository, times(1)).deleteById(1);
//    }

    //integration tests with real database
    @Test
    public void testCRUDOperationsWithRealDatabase() {
        Client client = new Client("John", "Doe", "Position", "password");
        realClientRepository.save(client);

        Optional<Client> retrievedClient = realClientRepository.findById(client.getUserId());
        assertEquals(client.getName(), retrievedClient.get().getName());

        client.setPosition("New Position");
        realClientRepository.save(client);
        Optional<Client> updatedClient = realClientRepository.findById(client.getUserId());
        assertEquals(client.getPosition(), updatedClient.get().getPosition());

        realClientRepository.delete(client);
        Optional<Client> deletedClient = realClientRepository.findById(client.getUserId());
        assertEquals(Optional.empty(), deletedClient);
    }
}
