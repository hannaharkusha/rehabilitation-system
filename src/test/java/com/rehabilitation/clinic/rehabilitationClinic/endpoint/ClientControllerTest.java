package com.rehabilitation.clinic.rehabilitationClinic.endpoint;

import com.rehabilitation.clinic.controller.ClientController;
import com.rehabilitation.clinic.entity.Client;
import com.rehabilitation.clinic.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetClientByEmail() {
        String email = "test@example.com";
        Client expectedClient = new Client("John", "Doe", "password", email, "12345678901", "123456789");

        when(clientService.getClientByEmail(email)).thenReturn(Optional.of(expectedClient));
        Optional<Client> actualClient = clientController.getClientByEmail(email);

        assertEquals(expectedClient, actualClient.orElse(null));
    }
}
