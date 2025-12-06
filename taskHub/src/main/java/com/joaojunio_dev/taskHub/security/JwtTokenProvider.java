package com.joaojunio_dev.taskHub.security;

import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Base64;

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


}
