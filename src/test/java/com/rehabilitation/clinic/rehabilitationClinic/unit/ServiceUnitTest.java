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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
                new Service("name1", 350, 30),
                new Service("name2", 1200, 90)
        );

        when(serviceRepository.findAll()).thenReturn(services);
        List<Service> result = serviceService.getAllServices();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetServiceById() {
        Service service = new Service("name", 100, 10);
        service.setServiceId(1);

        when(serviceRepository.findById(1)).thenReturn(Optional.of(service));
        Optional<Service> result = serviceService.getServiceById(1);
        assertTrue(result.isPresent());
        assertEquals(service.getName(), result.get().getName());
    }

    @Test
    public void testAddService() {
        Service service = new Service("name", 100, 10);
        service.setServiceId(1);

        when(serviceRepository.save(any(Service.class))).thenReturn(service);
        serviceService.addService("name", 100, 10);
        verify(serviceRepository, times(1)).save(any(Service.class));
    }

    @Test
    public void testDeleteServiceById() {
        serviceService.deleteServiceById(1);
        verify(serviceRepository, times(1)).deleteById(1);
    }
}