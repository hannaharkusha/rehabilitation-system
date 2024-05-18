package com.rehabilitation.clinic.rehabilitationClinic.unit;
import com.rehabilitation.clinic.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testAddMultipleEmployees() {
        employeeService.addEmployee("John", "Doe", "password123", "DR", "john.doe@example.com");
        employeeService.addEmployee("Jane", "Smith", "password123", "RE", "jane.smith@example.com");
        employeeService.addEmployee("Alice", "Johnson", "password123", "ADM", "alice.johnson@example.com");
        employeeService.addEmployee("Bob", "Brown", "password123", "DR", "bob.brown@example.com");
        employeeService.addEmployee("Charlie", "Davis", "password123", "DR", "charlie.davis@example.com");
        employeeService.addEmployee("David", "Wilson", "password123", "DR", "david.wilson@example.com");
        employeeService.addEmployee("Eva", "Moore", "password123", "DR", "eva.moore@example.com");
        employeeService.addEmployee("Frank", "Miller", "password123", "DR", "frank.miller@example.com");
        employeeService.addEmployee("Grace", "Taylor", "password123", "RE", "grace.taylor@example.com");
        employeeService.addEmployee("Hank", "Anderson", "password123", "RE", "hank.anderson@example.com");
        employeeService.addEmployee("Ivy", "Thomas", "password123", "DR", "ivy.thomas@example.com");
        employeeService.addEmployee("Jack", "Jackson", "password123", "DR", "jack.jackson@example.com");
        employeeService.addEmployee("Kara", "White", "password123", "DR", "kara.white@example.com");
        employeeService.addEmployee("Leo", "Harris", "password123", "DR", "leo.harris@example.com");
        employeeService.addEmployee("Mia", "Martin", "password123", "ADM", "mia.martin@example.com");
        employeeService.addEmployee("Nina", "Lee", "password123", "DR", "nina.lee@example.com");
        employeeService.addEmployee("Oscar", "Walker", "password123", "DR", "oscar.walker@example.com");
        employeeService.addEmployee("Paul", "Hall", "password123", "DR", "paul.hall@example.com");
        employeeService.addEmployee("Quinn", "Adams", "password123", "RE", "quinn.adams@example.com");
        employeeService.addEmployee("Rose", "Baker", "password123", "DR", "rose.baker@example.com");
    }
}