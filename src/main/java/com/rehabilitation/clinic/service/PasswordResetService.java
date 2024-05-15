package com.rehabilitation.clinic.service;

import com.rehabilitation.clinic.entity.Employee;
import com.rehabilitation.clinic.entity.PasswordResetToken;
import com.rehabilitation.clinic.repository.EmployeeRepository;
import com.rehabilitation.clinic.repository.PasswordResetTokenRepository;
import encoding.PasswordEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class PasswordResetService {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    public PasswordResetService(PasswordResetTokenRepository passwordResetTokenRepository) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    public Optional<PasswordResetToken> getCodeByEmail(String email) {
        try {
            if (email == null) {
                throw new IllegalArgumentException("PasswordResetService: incorrect email");
            }
            return passwordResetTokenRepository.findByEmail(email);
        } catch (Exception e) {
            System.err.println("Error retrieving code by email: " + e.getMessage());
            throw e;
        }
    }

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
        System.out.println("code sending activated");
        // Sender's email address
        final String senderEmail = "gabinetrehabinfo@gmail.com";
        // Sender's email password
        final String password = "fodu gdje zisb inak";

        // SMTP server details
        String host = "smtp.gmail.com";
        int port = 587;

        // Create properties for the SMTP session
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        // Create a Session object with the properties and authenticator
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);
            // Set sender email address
            message.setFrom(new InternetAddress(senderEmail));
            // Set recipient email address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            // Set email subject
            message.setSubject("Password Reset Verification Code");
            // Set email content
            message.setText("Your password reset verification code is: " + verificationCode);

            // Send the email
            Transport.send(message);

            System.out.println("Password reset verification code sent successfully to " + email);
        } catch (MessagingException e) {
            System.out.println("Error occurred while sending email: " + e.getMessage());
        }
    }


}
