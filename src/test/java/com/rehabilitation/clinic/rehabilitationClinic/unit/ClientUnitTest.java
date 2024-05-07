//package com.rehabilitation.clinic.rehabilitationClinic.unit;
//
//import com.rehabilitation.clinic.entity.Client;
//import com.rehabilitation.clinic.repository.ClientRepository;
//import com.rehabilitation.clinic.service.ClientService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class ClientUnitTest {
//    @Mock
//    private ClientRepository clientRepository;
//
//    @InjectMocks
//    private ClientService clientService;
//
//    @Test
//    public void testGetAllClients() {
//        List<Client> clients = Arrays.asList(
//                new Client("Jan", "Kowalski", "password"),
//                new Client("Anna", "Malinowska", "password")
//        );
//
//        when(clientRepository.findAll()).thenReturn(clients);
//        List<Client> result = clientService.getAllClients();
//        assertEquals(2, result.size());
//    }
//
//    @Test
//    public void testGetClientById() {
//        Client client = new Client("Jan", "Kowalski", "password");
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
//        Client client = new Client("Jan", "Kowalski", "password");
//        client.setUserId(1);
//
//        when(clientRepository.save(any(Client.class))).thenReturn(client);
//        clientService.addClient(client.getName(), client.getSurname(), client.getPassword());
//        verify(clientRepository, times(1)).save(any(Client.class));
//    }
//
//    @Test
//    public void testDeleteClientById() {
//        clientService.deleteClientById(1);
//        verify(clientRepository, times(1)).deleteById(1);
//    }
//}

