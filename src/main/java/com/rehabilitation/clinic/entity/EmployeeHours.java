package com.rehabilitation.clinic.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class EmployeeHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int EmployeeHoursId;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "userId")
    private Employee employee;
    private LocalDate dateWork;
    private LocalTime startTimeWork;
    private LocalTime endTimeWork;

    public Employee getEmployee()
    {
        return employee;
    }

    public void setEmployee(Employee employee)
    {
        this.employee = employee;
    }

    public LocalDate getDateWork() {
        return dateWork;
    }

    public void setDateWork(LocalDate dateWork) {
        this.dateWork = dateWork;
    }

    public LocalTime getStartTimeWork() {
        return startTimeWork;
    }

    public void setStartTimeWork(LocalTime startTimeWork) {
        this.startTimeWork = startTimeWork;
    }

    public LocalTime getEndTimeWork() {
        return endTimeWork;
    }

    public void setEndTimeWork(LocalTime endTimeWork) {
        this.endTimeWork = endTimeWork;
    }

    public EmployeeHours(){}
    public EmployeeHours(Employee employee, LocalDate dateWork, LocalTime startTimeWork, LocalTime endTimeWork) {
        this.employee = employee;
        this.dateWork = dateWork;
        this.startTimeWork = startTimeWork;
        this.endTimeWork = endTimeWork;
    }
}
