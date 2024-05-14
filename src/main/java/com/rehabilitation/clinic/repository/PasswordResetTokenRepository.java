package com.rehabilitation.clinic.repository;

import com.rehabilitation.clinic.entity.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, String> {
}
