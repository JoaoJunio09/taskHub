package com.joaojunio_dev.taskHub.infrastructure.email;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaojunio_dev.taskHub.config.EmailConfig;
import com.joaojunio_dev.taskHub.data.dto.email.EmailRequestDTO;
import com.joaojunio_dev.taskHub.mail.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class EmailService {

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private EmailConfig emailConfig;

    public void sendSimplesEmail(EmailRequestDTO emailRequest) {
        emailSender
            .to(emailRequest.getTo())
            .withSubject(emailRequest.getSubject())
            .withMessage(emailRequest.getBody())
            .send(emailConfig);
    }

    public void sendEmailWithAttachment(String emailRequestJson, MultipartFile attachment) {
        File tempFile = null;
        try {
            EmailRequestDTO emailRequest = new ObjectMapper().readValue(emailRequestJson, EmailRequestDTO.class);
            tempFile = File.createTempFile("attachment", attachment.getOriginalFilename());
            attachment.transferTo(tempFile);

            emailSender
                .to(emailRequest.getTo())
                .withSubject(emailRequest.getSubject())
                .withMessage(emailRequest.getBody())
                .attach(tempFile.getAbsolutePath())
                .send(emailConfig);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing email request JSON!");
        } catch (IOException ioE) {
            throw new RuntimeException("Error processing the attachment!");
        } finally {
            if (tempFile != null && tempFile.exists()) tempFile.delete();
        }
    }
}
