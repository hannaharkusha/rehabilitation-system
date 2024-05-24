package com.rehabilitation.clinic.service;

import com.rehabilitation.clinic.entity.Employee;
import com.rehabilitation.clinic.entity.EmployeeHours;
import com.rehabilitation.clinic.repository.EmployeeHoursRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeHoursService {
    private final EmployeeHoursRepository employeeHoursRepository;

    @Autowired
    public EmployeeHoursService(EmployeeHoursRepository employeeHoursRepository) {
        this.employeeHoursRepository = employeeHoursRepository;
    }

    public List<EmployeeHours> getAllEmployeeHours() {
        return employeeHoursRepository.findAll();
    }

    public Optional<EmployeeHours> getEmployeeHoursById(int id) {
        try {
            if(id <= 0) {
                throw new IllegalArgumentException("EmployeeHoursService: incorrect id");
            }
            return employeeHoursRepository.findById(id);
        } catch (Exception e) {
            System.err.println("Error retrieving employee hours: " + e.getMessage());
            throw e;
        }
    }

    public void addEmployeeHours(Employee employee, LocalDate dateWork, LocalTime startTimeWork, LocalTime endTimeWork) {
        try {
            if(employee == null || dateWork == null || startTimeWork == null || endTimeWork == null) {
                throw new IllegalArgumentException("EmployeeHoursService: incorrect data");
            }
            EmployeeHours employeeHours = new EmployeeHours(employee, dateWork, startTimeWork, endTimeWork);
            employeeHoursRepository.save(employeeHours);
        } catch (Exception e) {
            System.err.println("Error adding employee hours: " + e.getMessage());
            throw e;
        }
    }

    public void deleteEmployeeHoursById(int id) {
        try {
            if(id <= 0) {
                throw new IllegalArgumentException("EmployeeHoursService: incorrect id");
            }
            employeeHoursRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println("Error deleting employee hours: " + e.getMessage());
        }
    }

    public List<EmployeeHours> findEmployeeHoursByDate(LocalDate dateWork){
       return employeeHoursRepository.findEmployeeHoursByDate(dateWork);
    }

    public List<EmployeeHours> findEmployeeHoursByDateAndEmployee(LocalDate dateWork,int employeeId){
       return employeeHoursRepository.findEmployeeHoursByDateAndEmployee(dateWork,employeeId);
    }

}
