package com.rehabilitation.clinic.rehabilitationClinic.unit;

import com.rehabilitation.clinic.entity.Employee;
import com.rehabilitation.clinic.entity.EmployeeHours;
import com.rehabilitation.clinic.repository.EmployeeHoursRepository;
import com.rehabilitation.clinic.service.EmployeeHoursService;
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
public class EmployeeHoursUnitTest {
    @Mock
    private EmployeeHoursRepository employeeHoursRepository;

    @InjectMocks
    private EmployeeHoursService employeeHoursService;

    @Test
    public void testGetAllEmployeeHours() {
        Employee employee = new Employee("Jan", "Kowalski", "Rehabilitant", "password");
        List<EmployeeHours> employeeHours = Arrays.asList(
                new EmployeeHours(employee, LocalDate.of(2024, 3, 14), LocalTime.of(8, 0),
                        LocalTime.of(16, 0)),
                new EmployeeHours(employee, LocalDate.of(2024, 3, 15), LocalTime.of(8, 0),
                        LocalTime.of(16, 0)),
                new EmployeeHours(employee, LocalDate.of(2024, 3, 16), LocalTime.of(8, 0),
                        LocalTime.of(16, 0)),
                new EmployeeHours(employee, LocalDate.of(2024, 3, 17), LocalTime.of(8, 0),
                        LocalTime.of(16, 0))
        );

        when(employeeHoursRepository.findAll()).thenReturn(employeeHours);
        List<EmployeeHours> result = employeeHoursService.getAllEmployeeHours();
        assertEquals(4, result.size());
    }

    @Test
    public void testGetEmployeeHoursById() {
        Employee employee = new Employee("Jan", "Kowalski", "Rehabilitant", "password");
        EmployeeHours employeeHours = new EmployeeHours(employee, LocalDate.of(2024, 3, 14), LocalTime.of(8, 0),
                LocalTime.of(16, 0));
        employeeHours.setEmployeeHoursId(1);

        when(employeeHoursRepository.findById(1)).thenReturn(Optional.of(employeeHours));
        Optional<EmployeeHours> result = employeeHoursService.getEmployeeHoursById(1);
        assertTrue(result.isPresent());
        assertEquals(employeeHours.getEmployee(), result.get().getEmployee());
    }

    @Test
    public void testAddEmployeeHours() {
        Employee employee = new Employee("Jan", "Kowalski", "Rehabilitant", "password");
        EmployeeHours employeeHours = new EmployeeHours(employee, LocalDate.of(2024, 3, 14),
                LocalTime.of(8, 0), LocalTime.of(16, 0));
        employeeHours.setEmployeeHoursId(1);

        when(employeeHoursRepository.save(any(EmployeeHours.class))).thenReturn(employeeHours);
        employeeHoursService.addEmployeeHours(employeeHours.getEmployee(), employeeHours.getDateWork(),
                employeeHours.getStartTimeWork(), employeeHours.getEndTimeWork());
        verify(employeeHoursRepository, times(1)).save(any(EmployeeHours.class));
    }

    @Test
    public void testDeleteEmployeeHoursById() {
        employeeHoursService.deleteEmployeeHoursById(1);
        verify(employeeHoursRepository, times(1)).deleteById(1);
    }
}

