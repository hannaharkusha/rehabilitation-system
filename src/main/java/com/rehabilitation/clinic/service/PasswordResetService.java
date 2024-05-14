package com.rehabilitation.clinic.service;

import com.rehabilitation.clinic.entity.PasswordResetToken;
import com.rehabilitation.clinic.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordResetService {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    public void initiatePasswordReset(String email) {
        // Generate verification code
        String verificationCode = generateVerificationCode();

        // Store verification code in database
        PasswordResetToken token = new PasswordResetToken();
        token.setEmail(email);
        token.setVerificationCode(verificationCode);
        passwordResetTokenRepository.save(token);

        // Send email with verification code (implementation not shown here)
        sendPasswordResetEmail(email, verificationCode);
    }

    private String generateVerificationCode() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private void sendPasswordResetEmail(String email, String verificationCode) {
        System.out.println(verificationCode + email);
    }
}
