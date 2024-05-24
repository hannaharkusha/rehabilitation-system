package com.rehabilitation.clinic.repository;

import com.rehabilitation.clinic.entity.Visit;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer> {


    @Query("SELECT v FROM Visit v WHERE v.client.userId = :clientId")
    List<Visit> findByClientId(@Param("clientId") int clientId);

    @Query("SELECT v FROM Visit v WHERE v.employee.userId = :employeeId")
    List<Visit> findByEmployeeId(@Param("employeeId") int employeeId);

    @Transactional
    @Modifying
    @Query("UPDATE Visit v SET v.status = 'BOOKED', v.client.userId = :clientId WHERE v.visitId = :visitId")
    void updateVisitStatusAndClientId(@Param("visitId") int visitId,
                                       @Param("clientId") int patientId);

    @Query("SELECT v FROM Visit v WHERE v.employee.userId = :employeeId AND v.date = :date AND v.status = 'BOOKED'")
    List<Visit> findBookedVisitsByEmployeeAndDate(@Param("employeeId") int employeeId, @Param("date") LocalDate date);

    @Query("SELECT v FROM Visit v WHERE v.date BETWEEN :startDate AND :endDate AND v.status = 'FREE'")
    List<Visit> findFreeVisitsByDate(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT v FROM Visit v WHERE v.date BETWEEN :startDate AND :endDate AND v.status = 'FREE' AND v.service.serviceId = :serviceId")
    List<Visit> findFreeVisitsByDateAndService(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("serviceId") int serviceId);

}
