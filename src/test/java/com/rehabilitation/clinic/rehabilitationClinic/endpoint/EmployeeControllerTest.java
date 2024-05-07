package com.rehabilitation.clinic.rehabilitationClinic.endpoint;

import com.rehabilitation.clinic.controller.EmployeeController;
import com.rehabilitation.clinic.entity.Employee;
import com.rehabilitation.clinic.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetClientByEmail() {
        String email = "test@example.com";
        Employee expectedEmployee = new Employee("John", "Doe", "password",  "Rehabilitant", email);

        when(employeeService.getEmployeeByEmail(email)).thenReturn(Optional.of(expectedEmployee));
        Optional<Employee> actualClient = employeeController.getEmployeeByEmail(email);

        assertEquals(expectedEmployee, actualClient.orElse(null));
    }
}