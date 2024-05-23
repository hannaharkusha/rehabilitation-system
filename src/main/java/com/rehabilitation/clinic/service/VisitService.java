package com.rehabilitation.clinic.service;

import com.rehabilitation.clinic.entity.Client;
import com.rehabilitation.clinic.entity.Employee;
import com.rehabilitation.clinic.entity.Service;
import com.rehabilitation.clinic.entity.Visit;
import com.rehabilitation.clinic.repository.VisitRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@Transactional
public class VisitService {
    private final VisitRepository visitRepository;

    @Autowired
    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    public Optional<Visit> getVisitById(int id) {
        try {
            if(id <= 0) {
                throw new IllegalArgumentException("VisitService: incorrect id");
            }
            return visitRepository.findById(id);
        } catch (Exception e) {
            System.err. println("Error retrieving visit");
            throw e;
        }
    }

    public List<Visit> getVisitByClientId(int clientId){
        try {
            if(clientId <= 0) {
                throw new IllegalArgumentException("VisitService: incorrect clientId");
            }
            return visitRepository.findByClientId(clientId);
        } catch (Exception e) {
            System.err. println("Error retrieving visit");
            throw e;
        }
    }

    public List<Visit> getVisitByEmployeeId(int employeeId){
        try {
            if(employeeId <= 0) {
                throw new IllegalArgumentException("VisitService: incorrect EmployeeId");
            }
            return visitRepository.findByEmployeeId(employeeId);
        } catch (Exception e) {
            System.err. println("Error retrieving visit");
            throw e;
        }
    }
    public void addVisit(LocalDate date, LocalTime startTime, LocalTime endTime, Employee employee, Service service, Client client) {
        try {
            if(date == null || startTime == null || endTime == null || employee == null || service == null) {
                throw new IllegalArgumentException("VisitService: incorrect data");
            }
            Visit visit = new Visit(date, startTime, endTime, employee, service, client);
            visitRepository.save(visit);
        } catch (Exception e) {
            System.err.println("Error adding visit: " + e.getMessage());
        }
    }

    public void deleteVisitById(int id) {
        try {
            if(id <= 0) {
                throw new IllegalArgumentException("VisitService: incorrect id");
            }
            visitRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println("Error deleting visit: " + e.getMessage());
        }
    }
}
