package com.joaojunio_dev.taskHub.services;

import com.joaojunio_dev.taskHub.data.dto.security.AccountCredentialsDTO;
import com.joaojunio_dev.taskHub.data.dto.security.TokenDTO;
import com.joaojunio_dev.taskHub.exceptions.ObjectIsNullException;
import com.joaojunio_dev.taskHub.model.User;
import com.joaojunio_dev.taskHub.repositories.UserRepository;
import com.joaojunio_dev.taskHub.infrastructure.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final Logger logger = LoggerFactory.getLogger(AuthService.class.getName());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    UserRepository repository;

    public ResponseEntity<TokenDTO> signIn(AccountCredentialsDTO credentials) {
        logger.info("Performing the login");

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword()
            )
        );

        var user = repository.findByUsername(credentials.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("Username" + credentials.getUsername() + " not found!");
        }

        var token = tokenProvider.createAccessToken(
            credentials.getUsername(),
            user.getRoles()
        );
        return ResponseEntity.ok(token);
    }

    public ResponseEntity<TokenDTO> refreshToken(String username, String refreshToken) {
        var user = repository.findByUsername(username);
        TokenDTO token;
        if (user != null) {
            token = tokenProvider.refreshToken(refreshToken);
        } else {
            throw new ObjectIsNullException();
        }

        return ResponseEntity.ok(token);
    }

    public AccountCredentialsDTO create(AccountCredentialsDTO user) {

        User entity = new User();
        entity.setUsername(user.getUsername());
        entity.setPassword(generatedPassword(user.getPassword()));
        entity.setFullname(user.getFullname());
        entity.setEnabled(true);
        entity.setAccountNonLocked(true);
        entity.setAccountNonExpired(true);
        entity.setCredentialsNonExpired(true);

        var dto = repository.save(entity);
        return new AccountCredentialsDTO(dto.getUsername(), dto.getPassword(),dto.getFullname());
    }

    private String generatedPassword(String passwordString) {
        PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder(
                "", 8, 1815000,
                Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256
        );

        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("pbkdf2", pbkdf2Encoder);
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);

        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
        return passwordEncoder.encode(passwordString);
    }
}
