package com.joaojunio_dev.taskHub.config;

import jakarta.annotation.PostConstruct;
import nl.martijndwars.webpush.PushService;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

@Configuration
public class WebPushConfig {

    @Value("${web-push.vapid.public-key}")
    private String publicKey;

    @Value("${web-push.vapid.private-key}")
    private String privateKey;

    @Value("${web-push.vapid.subject}")
    private String subject;

    @Bean
    public PushService pushService() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        PushService pushService = new PushService();
        pushService.setPublicKey(publicKey);
        pushService.setPrivateKey(privateKey);
        pushService.setSubject(subject);
        return pushService;
    }

    @PostConstruct
    public void registerBouncyCastle() {
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }
}
