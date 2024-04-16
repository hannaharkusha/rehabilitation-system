package com.rehabilitation.clinic;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
public class EmployeeHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int EmployeeHoursId;

    private int employeeId;
    private LocalDate dateWork;
    private LocalTime startTimeWork;
    private LocalTime endTimeWork;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    public EmployeeHours(int employeeId, LocalDate dateWork, LocalTime startTimeWork, LocalTime endTimeWork) {
        this.employeeId = employeeId;
        this.dateWork = dateWork;
        this.startTimeWork = startTimeWork;
        this.endTimeWork = endTimeWork;
    }
}
