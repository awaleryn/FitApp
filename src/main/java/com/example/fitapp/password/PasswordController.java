package com.example.fitapp.password;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/password")
@RequiredArgsConstructor
public class PasswordController {

    private final PasswordResetService passwordResetService;

    @PatchMapping
    ResponseEntity<?> restoreForgottenPassword(
            @RequestParam String email
    ) {
        passwordResetService.initiatePasswordReset(email);
        return ResponseEntity.accepted().build();
    }
}
