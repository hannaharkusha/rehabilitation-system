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

import static org.junit.jupiter.api.Assertions.*;
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
                new Client("Jan", "Kowalski", "pass", "jan.k@email.com", "01010101010", "987654432"),
                new Client("Anna", "Malinowska", "pass", "anna.malinowska@email.com", "01010101010", "987654432")
                );

        when(clientRepository.findAll()).thenReturn(clients);
        List<Client> result = clientService.getAllClients();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetClientById_ValidId() {
        Client client = new Client("Jan", "Kowalski", "pass", "jan.k@email.com", "01010101010", "987654432");
        client.setUserId(1);

        when(clientRepository.findById(1)).thenReturn(Optional.of(client));
        Optional<Client> result = clientService.getClientById(1);
        assertTrue(result.isPresent());
        assertEquals(client.getName(), result.get().getName());
    }

    @Test
    public void testGetClientById_InvalidId() {
        int invalidClientId = -1;
        assertThrows(IllegalArgumentException.class, () -> clientService.getClientById(invalidClientId));
    }

    @Test
    public void testGetClientByEmail() {
        String email = "jan.k@email.com";
        Client client = new Client("Jan", "Kowalski", "pass", email, "01010101010", "987654432");

        when(clientRepository.findByEmail(email)).thenReturn(Optional.of(client));
        Optional<Client> result = clientService.getClientByEmail(email);
        assertTrue(result.isPresent());
        assertEquals(client.getName(), result.get().getName());
    }

    @Test
    public void testGetClientByPesel() {
        String pesel = "01010101010";
        Client client = new Client("Jan", "Kowalski", "pass", "jan.k@email.com", pesel, "987654432");

        when(clientRepository.findByPesel(pesel)).thenReturn(Optional.of(client));
        Optional<Client> result = clientService.getClientByPesel(pesel);
        assertTrue(result.isPresent());
        assertEquals(client.getName(), result.get().getName());
    }

    @Test
    public void testAddClient() {
        Client client = new Client("Jan", "Kowalski", "pass", "jan.k@email.com", "01010101010", "987654432");
        client.setUserId(1);

        when(clientRepository.save(any(Client.class))).thenReturn(client);
        clientService.addClient(client.getName(), client.getSurname(), client.getPassword(), client.getEmail(),
                client.getPesel(), client.getPhoneNr());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    public void testAddClient_InvalidData() {
        Client invalidClient = new Client(null, null, null, null, null, null);

        assertThrows(IllegalArgumentException.class, () -> clientService.addClient(
                invalidClient.getName(),
                invalidClient.getSurname(),
                invalidClient.getPassword(),
                invalidClient.getEmail(),
                invalidClient.getPesel(),
                invalidClient.getPhoneNr()
        ));
    }

    @Test
    public void testAddClientWithoutAccount() {
        Client clientNoAccount = new Client("Jan", "Kowalski", "01010101010", "987654432");
        clientNoAccount.setUserId(1);

        when(clientRepository.save(any(Client.class))).thenReturn(clientNoAccount);
        clientService.addClientWithoutAccount(clientNoAccount.getName(), clientNoAccount.getSurname(), clientNoAccount.getPesel(), clientNoAccount.getPhoneNr());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    public void testDeleteClientById() {
        clientService.deleteClientById(1);
        verify(clientRepository, times(1)).deleteById(1);
    }

    @Test
    public void testEditClient_ValidId() {
        int clientId = 1;
        String newName = "Jan";
        String newSurname = "Kowalski";
        String newEmail = "jan.k@email.com";
        String newPesel = "01010101010";
        String newPhoneNr = "987654432";

        Client existingClient = new Client("Emil", "Nowak", "pass", "stare@email.com", "12345678912", "987654321");
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));

        clientService.editClient(clientId, newName, newSurname, newEmail, newPesel, newPhoneNr);

        assertEquals(newName, existingClient.getName());
        assertEquals(newSurname, existingClient.getSurname());
        assertEquals(newEmail, existingClient.getEmail());
        assertEquals(newPesel, existingClient.getPesel());
        assertEquals(newPhoneNr, existingClient.getPhoneNr());
    }

    @Test
    public void testEditClient_InvalidId() {
        int invalidClientId = -1;
        assertThrows(IllegalArgumentException.class, () -> clientService.editClient(invalidClientId, "Name", "Surname", "email", "pesel", "phoneNr"));
    }

    @Test
    public void testEditClientPhoneNr_ValidId() {
        int clientId = 1;
        String newPhoneNr = "123456789";

        Client existingClient = new Client("Emil", "Nowak", "pass", "stare@email.com", "12345678912", "987654321");
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));

        clientService.editClientPhoneNr(clientId, newPhoneNr);

        assertEquals(newPhoneNr, existingClient.getPhoneNr());
    }

    @Test
    public void testEditClientPhoneNr_InvalidId() {
        int invalidClientId = -1;
        assertThrows(IllegalArgumentException.class, () -> clientService.editClientPhoneNr(invalidClientId, "987654321"));
    }

    @Test
    public void testEditClientPesel_ValidId() {
        int clientId = 1;
        String newPesel = "00000000001";

        Client existingClient = new Client("Emil", "Nowak", "pass", "stare@email.com", "12345678912", "987654321");
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));

        clientService.editClientPesel(clientId, newPesel);

        assertEquals(newPesel, existingClient.getPesel());
    }

    @Test
    public void testEditClientName_ValidId() {
        int clientId = 1;
        String newName = "Jan";

        Client existingClient = new Client("Emil", "Nowak", "pass", "stare@email.com", "12345678912", "987654321");
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));

        clientService.editClientName(clientId, newName);

        assertEquals(newName, existingClient.getName());
    }

    @Test
    public void testEditClientSurname_ValidId() {
        int clientId = 1;
        String newSurname = "Kowalski";

        Client existingClient = new Client("Emil", "Nowak", "pass", "stare@email.com", "12345678912", "987654321");
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));

        clientService.editClientSurname(clientId, newSurname);

        assertEquals(newSurname, existingClient.getSurname());
    }

    @Test
    public void testEditClientEmail_ValidId() {
        int clientId = 1;
        String newEmail = "nowe@example.com";

        Client existingClient = new Client("Emil", "Nowak", "pass", "stare@email.com", "12345678912", "987654321");
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));

        clientService.editClientEmail(clientId, newEmail);

        assertEquals(newEmail, existingClient.getEmail());
    }

    @Test
    public void testAuthenticateClient_InvalidCredentials() {
        String email = "jan.k@email.com";
        String incorrectPassword = "wrongpass";
        String hashedPassword = passwordEncoding.hashPassword(incorrectPassword);

        when(clientRepository.findByEmail(email)).thenReturn(Optional.empty());
        Optional<Client> authenticatedClient = clientService.authenticateClient(email, hashedPassword);

        assertFalse(authenticatedClient.isPresent());
    }
}