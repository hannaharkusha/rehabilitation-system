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


    //zmiana statusu wizyty
    //zmianaterminu wizyty
    //umowienie wizyty
}
