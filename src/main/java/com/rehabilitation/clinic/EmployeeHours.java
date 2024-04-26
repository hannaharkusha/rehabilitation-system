package com.rehabilitation.clinic;

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
    private Physiotherapist physiotherapist;
    private LocalDate dateWork;
    private LocalTime startTimeWork;
    private LocalTime endTimeWork;

    public Physiotherapist getPhysiotherapist()
    {
        return physiotherapist;
    }

    public void setPhysiotherapist(Physiotherapist physiotherapist)
    {
        this.physiotherapist = physiotherapist;
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
    public EmployeeHours(Physiotherapist physiotherapist, LocalDate dateWork, LocalTime startTimeWork, LocalTime endTimeWork) {
        this.physiotherapist = physiotherapist;
        this.dateWork = dateWork;
        this.startTimeWork = startTimeWork;
        this.endTimeWork = endTimeWork;
    }
}
