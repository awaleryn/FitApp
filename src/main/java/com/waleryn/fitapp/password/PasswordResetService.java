package com.waleryn.fitapp.password;

import com.waleryn.fitapp.exception.UserDoesNotExistException;
import com.waleryn.fitapp.user.User;
import com.waleryn.fitapp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PasswordResetService {

    private final JavaMailSender mailSender;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordResetService(
            UserRepository userRepository,
            JavaMailSender mailSender,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
    }

    public void initiatePasswordReset(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        var user = optionalUser.orElseThrow(
                () -> new UserDoesNotExistException("User with this login doesn't exist!"));

        String newPassword = PasswordRandomGenerator.generateRandomPassword();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Password Reset Request");
        message.setText("Your new password is: " + newPassword + "\nPlease change it after logging in.");

        mailSender.send(message);
    }

}
