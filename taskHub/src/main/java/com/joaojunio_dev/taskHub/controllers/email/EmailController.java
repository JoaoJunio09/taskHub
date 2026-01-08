package com.joaojunio_dev.taskHub.controllers.email;

import com.joaojunio_dev.taskHub.controllers.docs.EmailControllerDocs;
import com.joaojunio_dev.taskHub.data.dto.email.EmailRequestDTO;
import com.joaojunio_dev.taskHub.mediatype.MediaTypes;
import com.joaojunio_dev.taskHub.services.email.EmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "E-Mail")
@RestController
@RequestMapping("/api/v1/email")
public class EmailController implements EmailControllerDocs {

    @Autowired
    private EmailService emailService;

    @PostMapping(
        consumes = {
            MediaTypes.APPLICATION_JSON,
            MediaTypes.APPLICATION_XML,
            MediaTypes.APPLICATION_YAML })
    @Override
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDTO emailRequest) {
        emailService.sendSimplesEmail(emailRequest);
        return new ResponseEntity<>("e-Mail sent with success!", HttpStatus.OK);
    }

    @PostMapping(value = "/withAttachment")
    @Override
    public ResponseEntity<String> sendEmailWithAttachment(
        @RequestParam("emailRequest") String emailRequestJson,
        @RequestParam("attachment") MultipartFile attachment
    ) {
        emailService.sendEmailWithAttchment(emailRequestJson, attachment);
        return new ResponseEntity<>("e-Mail with Attachment sent successfully!", HttpStatus.OK);
    }
}
