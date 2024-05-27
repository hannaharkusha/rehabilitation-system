package com.rehabilitation.clinic.controller;

import com.rehabilitation.clinic.entity.Service;
import com.rehabilitation.clinic.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/service")
public class ServiceController {
    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/all")
    public List<Service> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/id")
    public Optional<Service> getServiceById(int id) {
        return serviceService.getServiceById(id);
    }

    @GetMapping("/add")
    public void addService(String name, float price) {
        serviceService.addService(name, price);
    }

    @GetMapping("/delete")
    public void deleteServiceById(int id) {
        serviceService.deleteServiceById(id);
    }


    //modyfikacji zabiegu
    @GetMapping("/edit")
    public void editService(int serviceId, String name, float price) {
        serviceService.editService(serviceId, name, price);
    }

}
