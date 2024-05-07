package com.rehabilitation.clinic.controller;

import com.rehabilitation.clinic.entity.Employee;
import com.rehabilitation.clinic.entity.EmployeeHours;
import com.rehabilitation.clinic.service.EmployeeHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hours")
public class EmployeeHoursController {
    private final EmployeeHoursService employeeHoursService;

    @Autowired
    public EmployeeHoursController(EmployeeHoursService employeeHoursService) {
        this.employeeHoursService = employeeHoursService;
    }

    @GetMapping("/all")
    public List<EmployeeHours> getAllEmployeeHours() {
        return employeeHoursService.getAllEmployeeHours();
    }

    @GetMapping("/id")
    public Optional<EmployeeHours> getEmployeeHoursById(int id) {
        return employeeHoursService.getEmployeeHoursById(id);
    }

    @GetMapping("/add")
    public void addEmployeeHours(Employee employee, LocalDate dateWork, LocalTime startTimeWork, LocalTime endTimeWork) {
        employeeHoursService.addEmployeeHours(employee, dateWork, startTimeWork, endTimeWork);
    }

    //zmiana godzin pracy pracownika

    @GetMapping("/delete")
    public void deleteEmployeeHoursById(int id) {
        employeeHoursService.deleteEmployeeHoursById(id);
    }
}
