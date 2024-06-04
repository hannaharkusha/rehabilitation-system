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

import static org.junit.jupiter.api.Assertions.*;
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
    public void testGetEmployeeById_ValidId() {
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
    public void testGetEmployeeById_InvalidId() {
        int invalidEmployeeId = -1;
        assertThrows(IllegalArgumentException.class, () -> employeeService.getEmployeeById(invalidEmployeeId));
    }

    @Test
    public void testGetEmployeeByEmail() {
        String email = "jan.k@email.com";
        Employee employee = new Employee("Jan", "Kowalski", "pass", "RE", email);

        when(employeeRepository.findByEmail(email)).thenReturn(Optional.of(employee));
        Optional<Employee> result = employeeService.getEmployeeByEmail(email);
        assertTrue(result.isPresent());
        assertEquals(employee.getName(), result.get().getName());
    }

    @Test
    public void testAddEmployee_ValidData() {
        String plainPassword = "password1";
        String hashedPassword = passwordEncoding.hashPassword(plainPassword);
        Employee employee = new Employee("Jan", "Kowalski", hashedPassword, "DR", "jan.kowalski@example.com");

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        employeeService.addEmployee(employee.getName(), employee.getSurname(), employee.getEmail(),
                employee.getPosition(), employee.getPassword());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    public void testAddEmployee_InvalidData() {
        Employee invalidClient = new Employee(null, null, null, null, null);

        assertThrows(IllegalArgumentException.class, () -> employeeService.addEmployee(
                invalidClient.getName(),
                invalidClient.getSurname(),
                invalidClient.getPassword(),
                invalidClient.getPosition(),
                invalidClient.getEmail()
        ));
    }

    @Test
    public void testDeleteEmployeeById() {
        employeeService.deleteEmployeeById(1);
        verify(employeeRepository, times(1)).deleteById(1);
    }

    @Test
    public void testEditEmployee_ValidId() {
        int employeeId = 1;
        String newName = "Jan";
        String newSurname = "Kowalski";
        String newPosition = "DR";
        String newEmail = "emil@example.com";

        Employee existingEmployee = new Employee("Emil", "Nowak", "pass", "RE", "12345678912");
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(existingEmployee));

        employeeService.editEmployee(employeeId, newName, newSurname, newPosition, newEmail);

        assertEquals(newName, existingEmployee.getName());
        assertEquals(newSurname, existingEmployee.getSurname());
        assertEquals(newPosition, existingEmployee.getPosition());
        assertEquals(newEmail, existingEmployee.getEmail());
    }
}