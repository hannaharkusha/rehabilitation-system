package com.rehabilitation.clinic.controller;

import com.rehabilitation.clinic.entity.Client;
import com.rehabilitation.clinic.entity.Employee;
import com.rehabilitation.clinic.entity.Service;
import com.rehabilitation.clinic.entity.Visit;
import com.rehabilitation.clinic.repository.ClientRepository;
import com.rehabilitation.clinic.repository.ServiceRepository;
import com.rehabilitation.clinic.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/visit")
public class VisitController {
    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/all")
    public List<Visit> getAllVisits() {
        return visitService.getAllVisits();
    }

    @GetMapping("/id")
    public Optional<Visit> getVisitById(int id) {
        return visitService.getVisitById(id);
    }

    @GetMapping("/add")
    public void addVisit(LocalDate date, LocalTime startTime, LocalTime endTime, Employee employee, Service service, Client client) {
        visitService.addVisit(date, startTime, endTime, employee, service, client);
    }

    @GetMapping("addDescription")
    public void addVisitDescription(int id, String result){
        visitService.addDescription(id, result);
    }

    @GetMapping("/add-withoutClient")
    public void addVisitWithoutClient(LocalDate date, LocalTime startTime, LocalTime endTime, Employee employee){
        visitService.addVisitWithoutClient(date, startTime, endTime, employee);
    }

    @GetMapping("/delete")
    public void deleteVisitById(int id) {
        visitService.deleteVisitById(id);
    }

    @GetMapping("/visit-clientid")
    public void getVisitByClientId(int clientId) {
        visitService.getVisitByClientId(clientId);
    }

    @GetMapping("/visit-employeeId")
    public void getVisitByEmployeeId(int employeeId) {
        visitService.getVisitByEmployeeId(employeeId);
    }

    //zmiana statusu wizyty - raczej nie trzeba jako osobna funkcja, to będzie realizowane w odwołaniu i umówieniu wizyty
    //zmianaterminu wizyty - funkcja odłowania wizyty i umówienia wizyty

    //umowienie wizyty

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ServiceRepository serviceRepository;
    @GetMapping("/book")
    public ResponseEntity<?> bookVisit(@RequestParam int clientId, @RequestParam int serviceId, @RequestParam int visitId) {
        try {
            Optional<Client> clientOptional = clientRepository.findById(clientId);
            Optional<Service> serviceOptional = serviceRepository.findById(serviceId);

            if (clientOptional.isPresent() && serviceOptional.isPresent()) {
                visitService.bookVisit(clientOptional.get(), serviceOptional.get(), visitId);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client or Service not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //odwołanie wizyty
    @GetMapping("/cancel-visit")
    public void cancelVisit(int visitId){visitService.cancelVisit(visitId);}

    //wizyty na wybrany dzien dla employee status booked
    @GetMapping("/booked-visits")
    public List<Visit> getBookedVisitsByEmployeeAndDate(int employeeId, LocalDate date) {
        return visitService.getBookedVisitsByEmployeeAndDate(employeeId, date);
    }

    //wolne wizyty z danego przedzialu czasowego
    @GetMapping("/by-date")
    public List<Visit> getFreeVisitsByDate(LocalDate startDate, LocalDate endDate) {
        return visitService.getFreeVisitsByDate(startDate, endDate);
    }

    //wolne wizyty z danego przedzialu czasowego z wybranym zabiegiem
    @GetMapping("/by-service")
    public List<Visit> getFreeVisitsByDateAndService(LocalDate startDate, LocalDate endDate, int serviceId) {
        return visitService.getFreeVisitsByDateAndService(startDate, endDate, serviceId);
    }
}
