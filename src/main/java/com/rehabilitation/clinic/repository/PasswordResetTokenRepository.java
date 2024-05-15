package com.rehabilitation.clinic.repository;

import com.rehabilitation.clinic.entity.Employee;
import com.rehabilitation.clinic.entity.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, String> {
    Optional<PasswordResetToken> findByEmail(String email);
}
