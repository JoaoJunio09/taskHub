package com.joaojunio_dev.taskHub.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.joaojunio_dev.taskHub.data.dto.security.TokenDTO;
import jakarta.annotation.PostConstruct;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-ket:57cr37}")
    private String secretKey = "57cr37";

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validationInMilisseconds = 3600000;

    @Autowired
    private UserDetailsService userService;

    Algorithm algorithm = null;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenDTO createAccessToken(String username, List<String> roles) {
        Date now = new Date();
        Date validation = new Date(now.getTime() + validationInMilisseconds);
        String accessToken = getAccessToken(username, now, validation, roles);
        String refreshToken = getRefreshToken(username, now, roles);
        return new TokenDTO(username, true, now, validation, accessToken, refreshToken);
    }

    private String getAccessToken(String username, Date now, Date validation, List<String> roles) {
        String isseurUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return JWT.create()
            .withClaim("roles", roles)
            .withSubject(username)
            .withIssuedAt(now)
            .withExpiresAt(validation)
            .withIssuer(isseurUrl)
            .sign(algorithm);
    }

    private String getRefreshToken(String username, Date now, List<String> roles) {
        Date refreshTokenValidation = new Date(now.getTime() + (validationInMilisseconds * 3));
        return JWT.create()
            .withClaim("roles", roles)
            .withSubject(username)
            .withIssuedAt(now)
            .withExpiresAt(refreshTokenValidation)
            .sign(algorithm);
    }
}
