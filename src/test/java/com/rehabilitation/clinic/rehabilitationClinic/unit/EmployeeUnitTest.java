package com.rehabilitation.clinic.rehabilitationClinic.unit;

import com.rehabilitation.clinic.entity.Employee;
import com.rehabilitation.clinic.repository.EmployeeRepository;
import com.rehabilitation.clinic.service.EmployeeService;
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
public class EmployeeUnitTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private PasswordEncoding passwordEncoding;

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = Arrays.asList(
                new Employee("Jan", "Kowalski", "pass", "Rehabilitant", "email"),
                new Employee("Piotr", "Kowalski", "pass", "Rehabilitant", "email"),
                new Employee("Tomasz", "Kowalski", "pass", "Rehabilitant", "email")
        );

        when(employeeRepository.findAll()).thenReturn(employees);
        List<Employee> result = employeeService.getAllEmployees();
        assertEquals(3, result.size());
    }

    @Test
    public void testGetEmployeeById() {
        String plainPassword = "pass";
        String hashedPassword = passwordEncoding.hashPassword(plainPassword);
        Employee employee = new Employee("Jan", "Kowalski", hashedPassword, "Rehabilitant", "email");
        employee.setUserId(1);

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        Optional<Employee> result = employeeService.getEmployeeById(1);
        assertTrue(result.isPresent());
        assertEquals(employee.getName(), result.get().getName());
    }

    @Test
    public void testAddEmployee() {
        String plainPassword = "pass";
        String hashedPassword = passwordEncoding.hashPassword(plainPassword);
        Employee employee = new Employee("Jan", "Kowalski", hashedPassword, "Rehabilitant", "email");
        employee.setUserId(1);

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        employeeService.addEmployee(employee.getName(), employee.getSurname(), employee.getEmail(),
                employee.getPosition(), employee.getPassword());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    public void testDeleteEmployeeById() {
        employeeService.deleteEmployeeById(1);
        verify(employeeRepository, times(1)).deleteById(1);
    }
}

