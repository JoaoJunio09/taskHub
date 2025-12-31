package com.joaojunio_dev.taskHub.controllers.email;

import com.joaojunio_dev.taskHub.model.Person;
import com.joaojunio_dev.taskHub.services.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody Person person) throws Exception {
        emailService.sendMailWithInline(person);

        Map<String, String> body = new HashMap<>();
        body.put("message", "Person created successfully.");

        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
