package com.rehabilitation.clinic.repository;

import com.rehabilitation.clinic.entity.EmployeeHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeHoursRepository extends JpaRepository<EmployeeHours, Integer> {

    @Query("SELECT e FROM EmployeeHours WHERE e.dateWork = :dateWork")
    List<EmployeeHours> findEmployeeHoursByDate(LocalDate dateWork);

    @Query("SELECT e FROM EmployeeHours WHERE e.dateWork = :dateWork AND e.employee.userId = :employeeId")
    List<EmployeeHours> findEmployeeHoursByDateAndEmployee(LocalDate dateWork,int employeeId);
}
