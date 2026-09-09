package com.ledgerpulse.backend.controller;

import com.ledgerpulse.backend.service.EmailService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/test")
    public ResponseEntity<String> sendTestEmail(@RequestBody EmailRequest request) {
        try {
            emailService.sendEmail(
                request.getTo(), 
                "LedgerPulse Test Email", 
                "This is a test email sent from LedgerPulse to verify the email configuration."
            );
            return ResponseEntity.ok("Test email sent successfully to " + request.getTo());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to send email: " + e.getMessage());
        }
    }
}

@Data
class EmailRequest {
    private String to;
}
