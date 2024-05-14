package com.rehabilitation.clinic.service;

import com.rehabilitation.clinic.entity.Service;
import com.rehabilitation.clinic.repository.ServiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@Transactional
public class ServiceService {
    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public Optional<Service> getServiceById(int id) {
        try {
            if(id <= 0) {
                throw new IllegalArgumentException("ServiceService: incorrect id");
            }
            return serviceRepository.findById(id);
        } catch (Exception e) {
            System.err. println("Error retrieving service");
            throw e;
        }
    }

    public void addService(String name, float price, int duration) {
        try {
            if(name == null || price < 0.0 || duration == 0) {
                throw new IllegalArgumentException("ServiceService: incorrect data");
            }
            Service service = new Service(name, price, duration);
            serviceRepository.save(service);
        } catch (Exception e) {
            System.err.println("Error adding service: " + e.getMessage());
        }
    }

    public void deleteServiceById(int id) {
        try {
            if(id <= 0) {
                throw new IllegalArgumentException("ServiceService: incorrect id");
            }
            serviceRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println("Error deleting service: " + e.getMessage());
        }
    }

    public void editService(int serviceId, String name, float price, int duration) {
        try {
            if ( serviceId <= 0) {
                throw new IllegalArgumentException("ServiceService: incorrect id");
            }

            Optional<Service> existingServiceOptional = serviceRepository.findById(serviceId);
            if (existingServiceOptional.isPresent()) {
                Service existingService = existingServiceOptional.get();
                existingService.setName(name);
                existingService.setPrice(price);
                existingService.setDuration(duration);

                serviceRepository.save(existingService);
            } else {
                throw new IllegalArgumentException("ServiceService: Service not found with id " +serviceId);
            }
        } catch (Exception e) {
            System.err.println("Error editing service: " + e.getMessage());
            throw e;
        }
    }

    public void editServiceName(int serviceId, String name) {
        try {
            if ( serviceId <= 0) {
                throw new IllegalArgumentException("ServiceService: incorrect id");
            }

            Optional<Service> existingServiceOptional = serviceRepository.findById(serviceId);
            if (existingServiceOptional.isPresent()) {
                Service existingService = existingServiceOptional.get();
                existingService.setName(name);

                serviceRepository.save(existingService);
            } else {
                throw new IllegalArgumentException("ServiceService: Service not found with id " +serviceId);
            }
        } catch (Exception e) {
            System.err.println("Error editing service: " + e.getMessage());
            throw e;
        }
    }
    public void editServicePrice(int serviceId, float price) {
        try {
            if ( serviceId <= 0) {
                throw new IllegalArgumentException("ServiceService: incorrect id");
            }

            Optional<Service> existingServiceOptional = serviceRepository.findById(serviceId);
            if (existingServiceOptional.isPresent()) {
                Service existingService = existingServiceOptional.get();
                existingService.setPrice(price);

                serviceRepository.save(existingService);
            } else {
                throw new IllegalArgumentException("ServiceService: Service not found with id " +serviceId);
            }
        } catch (Exception e) {
            System.err.println("Error editing service: " + e.getMessage());
            throw e;
        }
    }
    public void editServiceDuration(int serviceId, int duration) {
        try {
            if ( serviceId <= 0) {
                throw new IllegalArgumentException("ServiceService: incorrect id");
            }

            Optional<Service> existingServiceOptional = serviceRepository.findById(serviceId);
            if (existingServiceOptional.isPresent()) {
                Service existingService = existingServiceOptional.get();
                existingService.setDuration(duration);

                serviceRepository.save(existingService);
            } else {
                throw new IllegalArgumentException("ServiceService: Service not found with id " +serviceId);
            }
        } catch (Exception e) {
            System.err.println("Error editing service: " + e.getMessage());
            throw e;
        }
    }
}
