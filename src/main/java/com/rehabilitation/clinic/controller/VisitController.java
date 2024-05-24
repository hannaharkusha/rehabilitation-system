package com.rehabilitation.clinic.controller;

import com.rehabilitation.clinic.entity.Client;
import com.rehabilitation.clinic.entity.Employee;
import com.rehabilitation.clinic.entity.Service;
import com.rehabilitation.clinic.entity.Visit;
import com.rehabilitation.clinic.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @GetMapping("/booking-visit")
    public void bookVisitForClient(int clientId, int visitId){visitService.bookVisitForClient(clientId,visitId);}
    //odwołanie wizyty
    @GetMapping("/cancel-visit")
    public void cancelVisit(int visitId){visitService.cancelVisit(visitId);}

    //wizyty na wybrany dzien dla employee status booked
    //wolne wizyty z danego przedzialu czasowego
    //wolne wizyty z danego przedzialu czasowego z wybranym zabiegiem

}
