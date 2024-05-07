package com.rehabilitation.clinic.rehabilitationClinic.unit;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VisitUnitTest {
    @Mock
    private VisitRepository visitRepository;

    @InjectMocks
    private VisitService visitService;

    @Test
    public void testGetAllVisits() {
        Employee employee = new Employee("Jan", "Kowalski", "pass", "Rehabilitant", "email");
        Service service = new Service("name1", 350, 30);
        List<Visit> visits = Arrays.asList(
                new Visit(LocalDate.of(2024, 9, 17), LocalTime.of(12, 30),
                        LocalTime.of(13, 30), employee, service),
                new Visit(LocalDate.of(2024, 7, 17), LocalTime.of(10, 30),
                        LocalTime.of(16, 30), employee, service)
        );

        when(visitRepository.findAll()).thenReturn(visits);
        List<Visit> result = visitService.getAllVisits();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetVisitById() {
        Employee employee = new Employee("Jan", "Kowalski", "pass", "Rehabilitant", "email");
        Service service = new Service("name1", 350, 30);
        Visit visit = new Visit(LocalDate.of(2024, 9, 17), LocalTime.of(12, 30),
                        LocalTime.of(13, 30), employee, service);
        visit.setVisitId(1);

        when(visitRepository.findById(1)).thenReturn(Optional.of(visit));
        Optional<Visit> result = visitService.getVisitById(1);
        assertTrue(result.isPresent());
        assertEquals(visit.getDate(), result.get().getDate());
    }

    @Test
    public void testAddClient() {
        Employee employee = new Employee("Jan", "Kowalski", "pass", "Rehabilitant", "email");
        Service service = new Service("name1", 350, 30);
        Visit visit = new Visit(LocalDate.of(2024, 9, 17), LocalTime.of(12, 30),
                LocalTime.of(13, 30), employee, service);
        visit.setVisitId(1);

        when(visitRepository.save(any(Visit.class))).thenReturn(visit);
        visitService.addVisit(visit.getDate(), visit.getStartTime(), visit.getEndTime(), visit.getEmployee(), visit.getService());
        verify(visitRepository, times(1)).save(any(Visit.class));
    }

    @Test
    public void testDeleteVisitById() {
        visitService.deleteVisitById(1);
        verify(visitRepository, times(1)).deleteById(1);
    }
}

