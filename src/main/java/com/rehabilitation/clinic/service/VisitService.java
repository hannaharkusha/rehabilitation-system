package com.rehabilitation.clinic.service;

import com.rehabilitation.clinic.entity.Client;
import com.rehabilitation.clinic.entity.Employee;
import com.rehabilitation.clinic.entity.Service;
import com.rehabilitation.clinic.entity.Visit;
import com.rehabilitation.clinic.repository.ClientRepository;
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

    public void addDescription(int id, String result){
        try{
        Visit visit = visitRepository.findById(id).orElse(null);
        if(visit!=null){
            visit.setResult(result);
            visitRepository.save(visit);
        }else{
            throw new IllegalArgumentException("VisitService: no visit with such an id");
        }
        }catch(Exception e){
            System.err.println("Error adding visit description:");
            throw e;
        }
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

    @Autowired
    private ClientRepository clientRepository;

    public void bookVisit(Client client, Service service, int id) {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("VisitService: incorrect id");
            }

            Optional<Visit> existingVisitOptional = visitRepository.findById(id);
            if (existingVisitOptional.isPresent()) {
                Visit existingVisit = existingVisitOptional.get();

                clientRepository.save(client);

                existingVisit.setClient(client);
                existingVisit.setService(service);
                existingVisit.setStatus("BOOKED");

                visitRepository.save(existingVisit);
            } else {
                throw new IllegalArgumentException("VisitService: Visit not found with id " + id);
            }
        } catch (Exception e) {
            System.err.println("Error booking visit: " + e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void cancelVisit(int visitId)
    {
        Optional<Visit> optionalVisit = visitRepository.findById(visitId);

        optionalVisit.ifPresent(visit -> {
            visit.setStatus("FREE");
            visit.setClient(null);
            visit.setService(null);
            visitRepository.save(visit);
        });
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
    public void addVisitWithoutClient(LocalDate date, LocalTime startTime, LocalTime endTime, Employee employee) {
        try {
            if(date == null || startTime == null || endTime == null || employee == null) {
                throw new IllegalArgumentException("VisitService: incorrect data");
            }
            Visit visit = new Visit(date, startTime, endTime, employee);
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

    public List<Visit> getBookedVisitsByEmployeeAndDate(int employeeId, LocalDate date) {
        try {
            if(employeeId <= 0 || date == null) {
                throw new IllegalArgumentException("VisitService: incorrect data");
            }
            return visitRepository.findBookedVisitsByEmployeeAndDate(employeeId, date);
        } catch (Exception e) {
            System.err.println("Error retrieving booked visits by employee and date:");
            throw e;
        }
    }

    public List<Visit> getFreeVisitsByDate(LocalDate startDate, LocalDate endDate) {
        try {
            if(startDate == null || endDate == null) {
                throw new IllegalArgumentException("VisitService: incorrect date range");
            }
            return visitRepository.findFreeVisitsByDate(startDate, endDate);
        } catch (Exception e) {
            System.err.println("Error retrieving free visits by date range:");
            throw e;
        }
    }

    public List<Visit> getFreeVisitsByDateAndService(LocalDate startDate, LocalDate endDate, int serviceId) {
        try {
            if(startDate == null || endDate == null || serviceId <= 0) {
                throw new IllegalArgumentException("VisitService: incorrect data");
            }
            return visitRepository.findFreeVisitsByDateAndService(startDate, endDate, serviceId);
        } catch (Exception e) {
            System.err.println("Error retrieving free visits by date range and service:");
            throw e;
        }
    }
}
