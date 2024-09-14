package com.waleryn.fitapp.jwt;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.waleryn.fitapp.exception.WrongLogoutTokenException;
import com.waleryn.fitapp.token.TokenRepository;
import com.waleryn.fitapp.utils.Constants;
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
        if (authHeader == null || !authHeader.startsWith(Constants.BEARER)) {
            return;
        }
        jwt = authHeader.substring(Constants.BEARER_TOKEN);
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
