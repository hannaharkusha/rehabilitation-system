package com.rehabilitation.clinic.rehabilitationClinic.unit;

import com.rehabilitation.clinic.entity.Client;
import com.rehabilitation.clinic.entity.Employee;
import com.rehabilitation.clinic.entity.Service;
import com.rehabilitation.clinic.entity.Visit;
import com.rehabilitation.clinic.repository.VisitRepository;
import com.rehabilitation.clinic.service.VisitService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VisitUnitTest {
    @Mock
    private VisitRepository visitRepository;

    @InjectMocks
    private VisitService visitService;

    @Test
    public void testGetAllVisits() {
        LocalDate date = LocalDate.now();
        LocalTime start = LocalTime.of(12, 30);
        LocalTime end = LocalTime.of(16, 0);
        Employee employee = new Employee("Jan", "Kowalski", "pass", "Rehabilitant", "email");
        Service service = new Service("name1", 350);
        Client client = new Client("Jan", "Kowalski", "pass", "jan.k@email.com", "01010101010", "987654432");

        List<Visit> visits = Arrays.asList(
                new Visit(date, start, end, employee, service, client),
                new Visit(date, start, end, employee, service, client),
                new Visit(date, start, end, employee, service, client),
                new Visit(date, start, end, employee, service, client),
                new Visit(date, start, end, employee, service, client)
        );

        when(visitRepository.findAll()).thenReturn(visits);
        List<Visit> result = visitService.getAllVisits();
        assertEquals(5, result.size());
    }

    @Test
    public void testGetVisitById_ValidId() {
        LocalDate date = LocalDate.now();
        LocalTime start = LocalTime.of(12, 30);
        LocalTime end = LocalTime.of(16, 0);
        Employee employee = new Employee("Jan", "Kowalski", "pass", "Rehabilitant", "email");
        Service service = new Service("name1", 350);
        Client client = new Client("Jan", "Kowalski", "pass", "jan.k@email.com", "01010101010", "987654432");

        Visit visit = new Visit(date, start, end, employee, service, client);
        visit.setVisitId(1);

        when(visitRepository.findById(1)).thenReturn(Optional.of(visit));
        Optional<Visit> result = visitService.getVisitById(1);
        assertTrue(result.isPresent());
        assertEquals(visit.getDate(), result.get().getDate());
        assertEquals(visit.getStartTime(), result.get().getStartTime());
        assertEquals(visit.getEndTime(), result.get().getEndTime());
        assertEquals(visit.getEmployee(), result.get().getEmployee());
        assertEquals(visit.getService(), result.get().getService());
        assertEquals(visit.getClient(), result.get().getClient());
    }

    @Test
    public void testGetClientById_InvalidId() {
        int invalidVisitId = -1;
        assertThrows(IllegalArgumentException.class, () -> visitService.getVisitById(invalidVisitId));
    }

    @Test
    public void testBookVisit_InvalidId() {
        Service newService = new Service("name2", 450);
        Client newClient = new Client("Anna", "Nowak", "newpass", "anna.n@email.com", "02020202020", "123456789");

        when(visitRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> visitService.bookVisit(newClient, newService, 2));
    }

    @Test
    public void testCancelVisit_ValidId() {
        LocalDate date = LocalDate.now();
        LocalTime start = LocalTime.of(12, 30);
        LocalTime end = LocalTime.of(16, 0);
        Employee employee = new Employee("Jan", "Kowalski", "pass", "Rehabilitant", "email");
        Service service = new Service("name1", 350);
        Client client = new Client("Jan", "Kowalski", "pass", "jan.k@email.com", "01010101010", "987654432");

        Visit visit = new Visit(date, start, end, employee, service, client);
        visit.setVisitId(1);
        visit.setStatus("BOOKED");

        when(visitRepository.findById(1)).thenReturn(Optional.of(visit));
        when(visitRepository.save(visit)).thenReturn(visit);

        visitService.cancelVisit(1);

        assertEquals("FREE", visit.getStatus());
        assertNull(visit.getClient());
        assertNull(visit.getService());

        verify(visitRepository).findById(1);
        verify(visitRepository).save(visit);
    }

    @Test
    public void testCancelVisit_InvalidId() {
        when(visitRepository.findById(2)).thenReturn(Optional.empty());

        visitService.cancelVisit(2);

        verify(visitRepository).findById(2);
        verify(visitRepository, never()).save(any(Visit.class));
    }

    @Test
    public void testAddVisit_ValidData() {
        LocalDate date = LocalDate.now();
        LocalTime startTime = LocalTime.of(12, 30);
        LocalTime endTime = LocalTime.of(14, 30);
        Employee employee = new Employee("Jan", "Kowalski", "pass", "Rehabilitant", "email");
        Service service = new Service("name1", 350);
        Client client = new Client("Jan", "Kowalski", "pass", "jan.k@email.com", "01010101010", "987654432");

        visitService.addVisit(date, startTime, endTime, employee, service, client);
        verify(visitRepository).save(any(Visit.class));
    }

    @Test
    public void testAddVisitWithoutClient_ValidData() {
        LocalDate date = LocalDate.now();
        LocalTime startTime = LocalTime.of(12, 30);
        LocalTime endTime = LocalTime.of(14, 30);
        Employee employee = new Employee("Jan", "Kowalski", "pass", "Rehabilitant", "email");

        visitService.addVisitWithoutClient(date, startTime, endTime, employee);
        verify(visitRepository).save(any(Visit.class));
    }

    @Test
    public void testDeleteVisitById() {
        visitService.deleteVisitById(1);
        verify(visitRepository, times(1)).deleteById(1);
    }

    @Test
    public void testGetBookedVisitsByEmployeeAndDate_ValidData() {
        int employeeId = 1;
        LocalDate date = LocalDate.now();
        List<Visit> visits = Arrays.asList(new Visit(), new Visit());

        when(visitRepository.findBookedVisitsByEmployeeAndDate(employeeId, date)).thenReturn(visits);
        List<Visit> result = visitService.getBookedVisitsByEmployeeAndDate(employeeId, date);

        assertEquals(visits, result);
        verify(visitRepository).findBookedVisitsByEmployeeAndDate(employeeId, date);
    }

    @Test
    public void testGetBookedVisitsByEmployeeAndDate_InvalidData() {
        int employeeId = 0;
        LocalDate date = LocalDate.now();

        assertThrows(IllegalArgumentException.class, () -> visitService.getBookedVisitsByEmployeeAndDate(employeeId, date));
        verify(visitRepository, never()).findBookedVisitsByEmployeeAndDate(anyInt(), any(LocalDate.class));
    }

    @Test
    public void testGetFreeVisitsByDate_ValidData() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(1);
        List<Visit> visits = Arrays.asList(new Visit(), new Visit());

        when(visitRepository.findFreeVisitsByDate(startDate, endDate)).thenReturn(visits);
        List<Visit> result = visitService.getFreeVisitsByDate(startDate, endDate);

        assertEquals(visits, result);
        verify(visitRepository).findFreeVisitsByDate(startDate, endDate);
    }

    @Test
    public void testGetFreeVisitsByDate_InvalidData() {
        LocalDate startDate = null;
        LocalDate endDate = LocalDate.now().plusDays(1);

        assertThrows(IllegalArgumentException.class, () -> visitService.getFreeVisitsByDate(startDate, endDate));
        verify(visitRepository, never()).findFreeVisitsByDate(any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    public void testGetFreeVisitsByDateAndService_ValidData() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(1);
        int serviceId = 1;
        List<Visit> visits = Arrays.asList(new Visit(), new Visit());

        when(visitRepository.findFreeVisitsByDateAndService(startDate, endDate, serviceId)).thenReturn(visits);
        List<Visit> result = visitService.getFreeVisitsByDateAndService(startDate, endDate, serviceId);

        assertEquals(visits, result);
        verify(visitRepository).findFreeVisitsByDateAndService(startDate, endDate, serviceId);
    }

    @Test
    public void testGetFreeVisitsByDateAndService_InvalidData() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(1);
        int serviceId = 0;

        assertThrows(IllegalArgumentException.class, () -> visitService.getFreeVisitsByDateAndService(startDate, endDate, serviceId));
        verify(visitRepository, never()).findFreeVisitsByDateAndService(any(LocalDate.class), any(LocalDate.class), anyInt());
    }
}

