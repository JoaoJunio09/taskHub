package com.joaojunio_dev.taskHub.controllers.docs;

import com.joaojunio_dev.taskHub.data.dto.email.EmailRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface EmailControllerDocs {

    @Operation(
        tags = {"E-Mail"},
        description = "Sending simple E-mails!",
        summary = "Sending simple E-mails!",
        responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
        }
    )
    ResponseEntity<String> sendEmail(EmailRequestDTO emailRequest);

    @Operation(
        tags = {"E-Mail"},
        description = "Sending simple E-mail with attachment!",
        summary = "Sending simple E-mail with attachment!",
        responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
        }
    )
    ResponseEntity<String> sendEmailWithAttachment(String emailRequestJson,MultipartFile attachment);
}
