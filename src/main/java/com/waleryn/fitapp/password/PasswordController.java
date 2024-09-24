package com.waleryn.fitapp.password;

import com.waleryn.fitapp.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/password")
@RequiredArgsConstructor
@Validated
public class PasswordController {

    private final PasswordResetService passwordResetService;
    private final UserService userService;

    @PatchMapping("/forgot")
    ResponseEntity<?> restoreForgottenPassword(
            @RequestParam String email
    ) {
        passwordResetService.initiatePasswordReset(email);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.accepted().build();
    }
}
