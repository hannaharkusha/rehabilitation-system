package com.rehabilitation.clinic.rehabilitationClinic.unit;

import com.rehabilitation.clinic.entity.Client;
import com.rehabilitation.clinic.repository.ClientRepository;
import com.rehabilitation.clinic.service.ClientService;
import encoding.PasswordEncoding;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientUnitTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Mock
    private PasswordEncoding passwordEncoding;

    @Test
    public void testGetAllClients() {
        List<Client> clients = Arrays.asList(
                new Client("Jan", "Kowalski", "pass", "jan.k@email.com", "01010101010", "987654432", "ul. Wpadl w Pokrzywy"),
                new Client("Anna", "Malinowska", "pass", "anna.malinowska@email.com", "01010101010", "987654432", "ul. Pokrzywowa")
                );

        when(clientRepository.findAll()).thenReturn(clients);
        List<Client> result = clientService.getAllClients();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetClientById() {
        String plainPassword = "pass";
        String hashedPassword = passwordEncoding.hashPassword(plainPassword);
        Client client = new Client("Jan", "Kowalski", hashedPassword, "jan.k@email.com", "01010101010", "987654432", "ul. Dobra");

        client.setUserId(1);

        when(clientRepository.findById(1)).thenReturn(Optional.of(client));
        Optional<Client> result = clientService.getClientById(1);
        assertTrue(result.isPresent());
        assertEquals(client.getName(), result.get().getName());
    }


    @Test
    public void testGetClientByEmail() {
        String email = "jan.k@email.com";
        String plainPassword = "pass";
        String hashedPassword = passwordEncoding.hashPassword(plainPassword);
        Client client = new Client("Jan", "Kowalski", hashedPassword, email, "01010101010", "987654432", "ul. Dobra");

        when(clientRepository.findByEmail(email)).thenReturn(Optional.of(client));
        Optional<Client> result = clientService.getClientByEmail(email);
        assertTrue(result.isPresent());
        assertEquals(client.getName(), result.get().getName());
    }

    @Test
    public void testAddClient() {
        Client client = new Client("Jan", "Kowalski", "pass", "jan.k@email.com", "01010101010", "987654432", "ul. Dobra");
        client.setUserId(1);

        when(clientRepository.save(any(Client.class))).thenReturn(client);
        clientService.addClient(client.getName(), client.getSurname(), client.getPassword(), client.getEmail(),
                client.getPesel(), client.getPhoneNr(), client.getAddress());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    public void testDeleteClientById() {
        clientService.deleteClientById(1);
        verify(clientRepository, times(1)).deleteById(1);
    }
}

