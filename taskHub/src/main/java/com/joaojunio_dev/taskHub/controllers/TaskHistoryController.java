package com.joaojunio_dev.taskHub.controllers;

import com.joaojunio_dev.taskHub.controllers.docs.TaskHistoryControllerDocs;
import com.joaojunio_dev.taskHub.data.dto.TaskHistoryDTO;
import com.joaojunio_dev.taskHub.mediatype.MediaTypes;
import com.joaojunio_dev.taskHub.services.PersonService;
import com.joaojunio_dev.taskHub.services.TaskHistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        value = "/findByPerson/{personId}",
        produces = {
            MediaTypes.APPLICATION_JSON,
            MediaTypes.APPLICATION_XML,
            MediaTypes.APPLICATION_YAML })
    public ResponseEntity<List<TaskHistoryDTO>> findByPersonId(@PathVariable Long personId) {
        return ResponseEntity.ok().body(service.findByPersonId(personId));
    }

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
        value = "/export/byPerson/{personId}",
        produces = {
            MediaTypes.APPLICATION_CSV_VALUE,
            MediaTypes.APPLICATION_XLSX_VALUE,
            MediaTypes.APPLICATION_PDF_VALUE,
            MediaTypes.APPLICATION_JSON })
    @Override
    public ResponseEntity<?> exportByPersonId(
            @PathVariable Long personId,
            @RequestParam String routeGetProfileImage,
            HttpServletRequest request) {
        personService.findById(personId);

        String acceptHeader = request.getHeader(HttpHeaders.ACCEPT);
        var contentType = acceptHeader != null ? acceptHeader : "application/octet-stream";

        Resource file = service.exportByPersonId(acceptHeader, personId, routeGetProfileImage);

        Map<String, String> extensionMap = Map.of(
            MediaTypes.APPLICATION_CSV_VALUE, ".csv",
            MediaTypes.APPLICATION_XLSX_VALUE, ".xlsx",
            MediaTypes.APPLICATION_PDF_VALUE, ".pdf"
        );

        var extension = extensionMap.getOrDefault(acceptHeader, "");
        var filename = "tasks_history_by_person_exported" + extension;

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\""
            )
            .body(file);
    }
}
