package com.rehabilitation.clinic.repository;

import com.rehabilitation.clinic.entity.EmployeeHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeHoursRepository extends JpaRepository<EmployeeHours, Integer> {
}
