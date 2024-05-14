package com.rehabilitation.clinic.controller;

import com.rehabilitation.clinic.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reset")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @GetMapping("/password")
    public void addToken(String email) {
        // Call service method to handle password reset
        passwordResetService.initiatePasswordReset(email);
    }
}
