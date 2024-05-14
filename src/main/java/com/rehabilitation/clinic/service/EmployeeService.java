package com.rehabilitation.clinic.service;

import com.rehabilitation.clinic.entity.Employee;
import com.rehabilitation.clinic.repository.EmployeeRepository;
import encoding.PasswordEncoding;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoding passwordEncoding;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoding = new PasswordEncoding();
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(int id) {
        try {
            if(id <= 0) {
                throw new IllegalArgumentException("EmployeeService: incorrect id");
            }
            return employeeRepository.findById(id);
        } catch (Exception e) {
            System.err.println("Error retrieving employee: " + e.getMessage());
            throw e;
        }
    }

    public Optional<Employee> getEmployeeByEmail(String email) {
        try {
            if (email == null) {
                throw new IllegalArgumentException("EmployeeService: incorrect email");
            }
            return employeeRepository.findByEmail(email);
        } catch (Exception e) {
            System.err.println("Error retrieving employee by email: " + e.getMessage());
            throw e;
        }
    }

    public void addEmployee(String name, String surname, String password, String position, String email) {
        try {
            if(name == null || surname == null || password == null || position == null) {
                throw new IllegalArgumentException("EmployeeService: incorrect data");
            }
            String hashedPassword = passwordEncoding.hashPassword(password);
            Employee employee = new Employee(name, surname, hashedPassword, position,email);
            employeeRepository.save(employee);
        } catch (Exception e) {
            System.err.println("Error adding employee: " + e.getMessage());
            throw e;
        }
    }

    public void deleteEmployeeById(int id) {
        try {
            if(id <= 0) {
                throw new IllegalArgumentException("EmployeeService: incorrect id");
            }
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println("Error deleting employee: " + e.getMessage());
        }
    }

    public Optional<Employee> authenticateEmployee(String email, String password) {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(email);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            if (passwordEncoding.checkPassword(password, employee.getPassword())) {
                return Optional.of(employee);
            }
        }
        return Optional.empty();
    }
}
