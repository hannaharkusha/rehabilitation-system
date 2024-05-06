package com.rehabilitation.clinic.controller;

import com.rehabilitation.clinic.entity.Employee;
import com.rehabilitation.clinic.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/id")
    public Optional<Employee> getEmployeeById(int id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/add")
    public void addEmployee(String name, String surname, String password ,String position) {
        employeeService.addEmployee(name, surname, password, position);
    }

    @GetMapping("/delete")
    public void deleteEmployeeById(int id) {
        employeeService.deleteEmployeeById(id);
    }
}