package com.joaojunio_dev.taskHub.controllers;

import com.joaojunio_dev.taskHub.controllers.docs.TaskControllerDocs;
import com.joaojunio_dev.taskHub.data.dto.TaskDTO;
import com.joaojunio_dev.taskHub.mediatype.MediaTypes;
import com.joaojunio_dev.taskHub.model.enums.ThisDateOrPreviousOrLater;
import com.joaojunio_dev.taskHub.services.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Task")
@RestController
@RequestMapping("/api/tasks/v1")
public class TaskController implements TaskControllerDocs {

    @Autowired
    private TaskService service;

    @GetMapping(
        produces = {
            MediaTypes.APPLICATION_JSON,
            MediaTypes.APPLICATION_XML,
            MediaTypes.APPLICATION_YAML })
    @Override
    public ResponseEntity<List<TaskDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(
        value = "/{id}",
        produces = {
            MediaTypes.APPLICATION_JSON,
            MediaTypes.APPLICATION_XML,
            MediaTypes.APPLICATION_YAML })
    @Override
    public ResponseEntity<TaskDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping(
        value = "/findByPersonId/{personId}",
        produces = {
            MediaTypes.APPLICATION_JSON,
            MediaTypes.APPLICATION_XML,
            MediaTypes.APPLICATION_YAML })
    @Override
    public ResponseEntity<List<TaskDTO>> findByPersonId(@PathVariable Long personId) {
        return ResponseEntity.ok().body(service.findByPersonId(personId));
    }

    @GetMapping(
        value = "/findByCompleted/{completed}",
        produces = {
            MediaTypes.APPLICATION_JSON,
            MediaTypes.APPLICATION_XML,
            MediaTypes.APPLICATION_YAML })
    @Override
    public ResponseEntity<List<TaskDTO>> findByCompleted(@PathVariable Boolean completed) {
        return ResponseEntity.ok().body(service.findByCompleted(completed));
    }

    @GetMapping(
        value = "/findByDate",
        produces = {
            MediaTypes.APPLICATION_JSON,
            MediaTypes.APPLICATION_XML,
            MediaTypes.APPLICATION_YAML })
    @Override
    public ResponseEntity<List<TaskDTO>> findByDate(
        @PathParam("date") LocalDate date,
        @PathParam("type")ThisDateOrPreviousOrLater type) {
        return ResponseEntity.ok().body(service.findByDate(date, type));
    }

    @PostMapping(
        consumes = {
            MediaTypes.APPLICATION_JSON,
            MediaTypes.APPLICATION_XML,
            MediaTypes.APPLICATION_YAML },
        produces = {
            MediaTypes.APPLICATION_JSON,
            MediaTypes.APPLICATION_XML,
            MediaTypes.APPLICATION_YAML })
    @Override
    public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO task) {
        return ResponseEntity.ok().body(service.create(task));
    }

    @PutMapping(
        consumes = {
            MediaTypes.APPLICATION_JSON,
            MediaTypes.APPLICATION_XML,
            MediaTypes.APPLICATION_YAML},
        produces = {
            MediaTypes.APPLICATION_JSON,
            MediaTypes.APPLICATION_XML,
            MediaTypes.APPLICATION_YAML })
    @Override
    public ResponseEntity<TaskDTO> update(@RequestBody TaskDTO task) {
        return ResponseEntity.ok().body(service.update(task));
    }

    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
