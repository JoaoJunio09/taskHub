package com.joaojunio_dev.taskHub.controllers.docs;

import com.joaojunio_dev.taskHub.mediatype.MediaTypes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface TaskHistoryControllerDocs {

    @Operation(
        tags = {"Task History"},
        summary = "Export data of Task's in CSV, XLSX and PDF",
        description = "Export data of Task's in CSV, XLSX and PDF",
        responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                @Content(mediaType = MediaTypes.APPLICATION_JSON),
                @Content(mediaType = MediaTypes.APPLICATION_CSV_VALUE),
                @Content(mediaType = MediaTypes.APPLICATION_XLSX_VALUE),
                @Content(mediaType = MediaTypes.APPLICATION_PDF_VALUE),
            }),
            @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
        }
    )
    ResponseEntity<Resource> export(HttpServletRequest request);

    @Operation(
        tags = {"Task History"},
        summary = "Export data of Task's in CSV, XLSX and PDF by Person ID",
        description = "Export data of Task's in CSV, XLSX and PDF by Person ID",
        responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                @Content(mediaType = MediaTypes.APPLICATION_CSV_VALUE),
                @Content(mediaType = MediaTypes.APPLICATION_XLSX_VALUE),
                @Content(mediaType = MediaTypes.APPLICATION_PDF_VALUE),
            }),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
        }
    )
    ResponseEntity<?> exportByPersonId(Long personId, String routeGetProfileImage, HttpServletRequest request);
}
