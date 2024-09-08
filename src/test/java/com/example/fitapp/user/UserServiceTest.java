package com.example.fitapp.user;

import com.example.fitapp.exception.PasswordsNotMatchingException;
import com.example.fitapp.exception.WrongPasswordException;
import com.example.fitapp.password.ChangePasswordRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.security.Principal;

import static com.example.fitapp.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setEmail("testuser");
        user.setPassword("encodedCurrentPassword");
    }

    @Test
    void shouldThrowWrongPasswordExceptionWhenCurrentPasswordIsIncorrect() {
        ChangePasswordRequest request = new ChangePasswordRequest(
                "wrongCurrentPassword", "newPassword", "newPassword"
        );

        Principal principal = new UsernamePasswordAuthenticationToken(user, null);

        when(passwordEncoder.matches(request.getCurrentPassword(), user.getPassword()))
                .thenReturn(false);

        assertThrows(WrongPasswordException.class, () -> userService.changePassword(request, principal));

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void shouldThrowPasswordsNotMatchingExceptionWhenNewPasswordDoesNotMatchConfirmation() {
        ChangePasswordRequest request = new ChangePasswordRequest(
                "currentPassword", "newPassword", "differentConfirmationPassword"
        );

        Principal principal = new UsernamePasswordAuthenticationToken(user, null);

        when(passwordEncoder.matches(request.getCurrentPassword(), user.getPassword()))
                .thenReturn(true);

        assertThrows(PasswordsNotMatchingException.class, () -> userService.changePassword(request, principal));

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void shouldChangePasswordWhenAllConditionsAreMet() {
        ChangePasswordRequest request = new ChangePasswordRequest(
                "currentPassword", "newPassword", "newPassword"
        );

        Principal principal = new UsernamePasswordAuthenticationToken(user, null);


        when(passwordEncoder.matches(request.getCurrentPassword(), user.getPassword()))
                .thenReturn(true);
        when(passwordEncoder.encode(request.getNewPassword()))
                .thenReturn("encodedNewPassword");

        userService.changePassword(request, principal);

        verify(userRepository, times(ONE_TIME)).save(user);
        assertEquals("encodedNewPassword", user.getPassword());
    }
}