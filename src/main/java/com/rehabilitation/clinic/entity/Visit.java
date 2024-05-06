package com.rehabilitation.clinic.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int visitId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "physiotherapist_id", referencedColumnName = "userId")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "serviceId")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "userId")
    private Client client;

    private enum Status {
        CANCELLED, PENDING, COMPLETED, INPROGRESS, FREE, BOOKED
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public void setStatus(String s) {
        if (s.equalsIgnoreCase("CANCELLED")) {
            this.status = Status.CANCELLED;
        } else if (s.equalsIgnoreCase("PENDING")) {
            this.status = Status.PENDING;
        } else if (s.equalsIgnoreCase("COMPLETED")) {
            this.status = Status.COMPLETED;
        } else if (s.equalsIgnoreCase("INPROGRESS")) {
            this.status = Status.INPROGRESS;
        } else if (s.equalsIgnoreCase("FREE")) {
            this.status = Status.FREE;
        } else if (s.equalsIgnoreCase("BOOKED")) {
            this.status = Status.BOOKED;
        }

    }

    public String getStatus() {
        String stat = null;
        if (this.status.equals(Status.FREE)) {
            stat = "FREE";
        } else if (status.equals(Status.BOOKED)) {
            stat = "BOOKED";
        } else if (status.equals(Status.CANCELLED)) {
            stat = "CANCELLED";
        } else if (status.equals(Status.PENDING)) {
            stat = "PENDING";
        } else if (status.equals(Status.COMPLETED)) {
            stat = "COMPLETED";
        } else if (status.equals(Status.INPROGRESS)) {
            stat = "INPROGRESS";
        }

        return stat;
    }

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Visit() {}

    public Visit(LocalDate date, LocalTime startTime, LocalTime endTime, Employee employee, Service service) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.employee = employee;
        this.service = service;
        this.status = Status.FREE;
    }


}