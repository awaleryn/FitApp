package com.example.fitapp.jwt;

import static com.example.fitapp.utils.Constants.*;

import com.example.fitapp.exception.WrongLogoutTokenException;
import com.example.fitapp.token.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) {
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String jwt;
        if (authHeader == null || !authHeader.startsWith(BEARER)) {
            return;
        }
        jwt = authHeader.substring(BEARER_TOKEN);
        var storedToken = tokenRepository.findByToken(jwt)
                .orElse(null);

        if (storedToken != null
                && !storedToken.isRevoked()
                && !storedToken.isExpired()) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
        } else {
            throw new WrongLogoutTokenException("Wrong token or token already expired!");
        }
    }
}
