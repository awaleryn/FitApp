package com.example.fitapp.user;

import com.example.fitapp.exception.PasswordsNotMatchingException;
import com.example.fitapp.exception.WrongPasswordException;
import com.example.fitapp.password.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void changePassword(
            ChangePasswordRequest request,
            Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if (isCurrentPasswordNotMatchingCurrentPasswordRequest(request.getCurrentPassword(), user.getPassword())) {
            throw new WrongPasswordException("Wrong password");
        }
        logger.info("Current password and requested current password match");

        if (isNewPasswordNotMatchingConfirmationPassword(request.getNewPassword(), request.getConfirmationPassword())) {
            throw new PasswordsNotMatchingException("Passwords are not the same");
        }
        logger.info("New password and confirmation password match");

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        logger.info("Password changed for user: " + user.getUsername());
    }

    private boolean isCurrentPasswordNotMatchingCurrentPasswordRequest(
            String currentPassword,
            String newPassword) {
        return !passwordEncoder.matches(currentPassword, newPassword);
    }

    private boolean isNewPasswordNotMatchingConfirmationPassword(
            String newPassword,
            String confirmationPassword) {
        return !newPassword.equals(confirmationPassword);
    }
}
