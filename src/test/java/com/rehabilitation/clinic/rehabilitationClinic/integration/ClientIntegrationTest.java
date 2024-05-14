package com.rehabilitation.clinic.rehabilitationClinic.integration;

import com.rehabilitation.clinic.entity.Client;
import com.rehabilitation.clinic.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith({MockitoExtension.class})
public class ClientIntegrationTest {

    @Autowired
    private ClientRepository clientRepository;

    //integration tests with real database
    @Test
    public void testCRUDOperationsWithRealDatabase() {
        Client client = new Client("Jan", "Kowalski", "pass", "jan.k@email.com", "01010101010", "987654432");
        clientRepository.save(client);

        Optional<Client> retrievedClient = clientRepository.findById(client.getUserId());
        assertEquals(client.getName(), retrievedClient.get().getName());

        clientRepository.delete(client);
        Optional<Client> deletedClient = clientRepository.findById(client.getUserId());
        assertEquals(Optional.empty(), deletedClient);
    }
}