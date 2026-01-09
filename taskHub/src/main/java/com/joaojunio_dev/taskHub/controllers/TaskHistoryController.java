package com.joaojunio_dev.taskHub.controllers;

import com.joaojunio_dev.taskHub.controllers.docs.TaskHistoryControllerDocs;
import com.joaojunio_dev.taskHub.mediatype.MediaTypes;
import com.joaojunio_dev.taskHub.data.dto.ApiResponse;
import com.joaojunio_dev.taskHub.services.PersonService;
import com.joaojunio_dev.taskHub.services.TaskHistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@Tag(name = "Task History")
@RestController
@RequestMapping("/api/tasksHistory/v1")
public class TaskHistoryController implements TaskHistoryControllerDocs {

    @Autowired
    private TaskHistoryService service;

    @Autowired
    private PersonService personService;

    @GetMapping(
        value = "/export",
        produces = {
            MediaTypes.APPLICATION_CSV_VALUE,
            MediaTypes.APPLICATION_XLSX_VALUE,
            MediaTypes.APPLICATION_PDF_VALUE })
    @Override
    public ResponseEntity<Resource> export(HttpServletRequest request) {
        String acceptHeader = request.getHeader(HttpHeaders.ACCEPT);

        Resource file = service.export(acceptHeader);

        Map<String, String> extensionMap = Map.of(
            MediaTypes.APPLICATION_CSV_VALUE, ".csv",
            MediaTypes.APPLICATION_XLSX_VALUE, ".xlsx",
            MediaTypes.APPLICATION_PDF_VALUE, ".pdf"
        );

        var extension = extensionMap.getOrDefault(acceptHeader, "");
        var contentType = acceptHeader != null ? acceptHeader : "application/octet-stream";
        var filename = "tasks_history_exported" + extension;

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\""
            )
            .body(file);
    }

    @GetMapping(
        value = "/export/by-person/{personId}",
        produces = {
            MediaTypes.APPLICATION_JSON,
            MediaTypes.APPLICATION_CSV_VALUE,
            MediaTypes.APPLICATION_XLSX_VALUE,
            MediaTypes.APPLICATION_PDF_VALUE })
    @Override
    public ResponseEntity<?> exportByPersonId(@PathVariable Long personId, HttpServletRequest request) {
        personService.findById(personId);

        String acceptHeader = request.getHeader(HttpHeaders.ACCEPT);
        var contentType = acceptHeader != null ? acceptHeader : "application/octet-stream";

        if (service.findByPersonId(personId).isEmpty()) {
            return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                    new ApiResponse(
                        "This person has no task history to export!",
                        200,
                        LocalDateTime.now().toString()));
        }

        Resource file = service.exportByPersonId(acceptHeader, personId);

        Map<String, String> extensionMap = Map.of(
            MediaTypes.APPLICATION_CSV_VALUE, ".csv",
            MediaTypes.APPLICATION_XLSX_VALUE, ".xlsx",
            MediaTypes.APPLICATION_PDF_VALUE, ".pdf"
        );

        var extension = extensionMap.getOrDefault(acceptHeader, "");
        var filename = "tasks_history_exported" + extension;

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\""
            )
            .body(file);
    }
}
