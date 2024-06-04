package com.rehabilitation.clinic.rehabilitationClinic.unit;

import com.rehabilitation.clinic.entity.Service;
import com.rehabilitation.clinic.repository.ServiceRepository;
import com.rehabilitation.clinic.service.ServiceService;
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
public class ServiceUnitTest {
    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private ServiceService serviceService;

    @Test
    public void testGetAllServices() {
        List<Service> services = Arrays.asList(
                new Service("name1", 350),
                new Service("name2", 1200)
        );

        when(serviceRepository.findAll()).thenReturn(services);
        List<Service> result = serviceService.getAllServices();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetServiceById_ValidId() {
        Service service = new Service("name", 100);
        service.setServiceId(1);

        when(serviceRepository.findById(1)).thenReturn(Optional.of(service));
        Optional<Service> result = serviceService.getServiceById(1);
        assertTrue(result.isPresent());
        assertEquals(service.getName(), result.get().getName());
    }

    @Test
    public void testGetServiceById_InvalidId() {
        int invalidServiceId = -1;
        assertThrows(IllegalArgumentException.class, () -> serviceService.getServiceById(invalidServiceId));
    }

    @Test
    public void testAddService() {
        Service service = new Service("name", 100);
        service.setServiceId(1);

        when(serviceRepository.save(any(Service.class))).thenReturn(service);
        serviceService.addService("name", 100);
        verify(serviceRepository, times(1)).save(any(Service.class));
    }

    @Test
    public void testDeleteServiceById() {
        serviceService.deleteServiceById(1);
        verify(serviceRepository, times(1)).deleteById(1);
    }

    @Test
    public void testEditService_ValidId() {
        int serviceId = 1;
        String newName = "Nowy";
        float newPrice = 95;

        Service existingService = new Service("Stary", 0);
        when(serviceRepository.findById(serviceId)).thenReturn(Optional.of(existingService));

        serviceService.editService(serviceId, newName, newPrice);

        assertEquals(newName, existingService.getName());
        assertEquals(newPrice, existingService.getPrice());
    }

    @Test
    public void testEditService_InvalidId() {
        int invalidServiceId = -1;
        assertThrows(IllegalArgumentException.class, () -> serviceService.editService(invalidServiceId, "Name", 95));
    }

    @Test
    public void testEditServiceName_ValidId() {
        int serviceId = 1;
        String newName = "Nowe";

        Service existingService = new Service("Stare", 35);
        when(serviceRepository.findById(serviceId)).thenReturn(Optional.of(existingService));

        serviceService.editServiceName(serviceId, newName);

        assertEquals(newName, existingService.getName());
    }

    @Test
    public void testEditServicePrice_ValidId() {
        int serviceId = 1;
        float newPrice = 100;

        Service existingService = new Service("Stare", 35);
        when(serviceRepository.findById(serviceId)).thenReturn(Optional.of(existingService));

        serviceService.editServicePrice(serviceId, newPrice);

        assertEquals(newPrice, existingService.getPrice());
    }
}