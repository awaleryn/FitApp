package com.waleryn.fitapp.auth;

import com.waleryn.fitapp.user.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register (
            @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login (
            @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateUser (
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken (
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        authService.refreshToken(request, response);
    }
}
