package com.joaojunio_dev.taskHub.controllers;

import com.joaojunio_dev.taskHub.controllers.docs.TaskControllerDocs;
import com.joaojunio_dev.taskHub.data.dto.TaskDTO;
import com.joaojunio_dev.taskHub.mediatype.MediaType;
import com.joaojunio_dev.taskHub.services.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Task")
@RestController
@RequestMapping("/api/v1/task")
public class TaskController implements TaskControllerDocs {

    @Autowired
    private TaskService service;

    @GetMapping(
        produces = {
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML}
    )
    @Override
    public ResponseEntity<List<TaskDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(
        value = "/{id}",
        produces = {
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML}
    )
    @Override
    public ResponseEntity<TaskDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping(
        consumes = {
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML},
        produces = {
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML}
    )
    @Override
    public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO task) {
        return ResponseEntity.ok().body(service.create(task));
    }

    @PutMapping(
        consumes = {
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML},
        produces = {
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML}
    )
    @Override
    public ResponseEntity<TaskDTO> update(@RequestBody TaskDTO task) {
        return ResponseEntity.ok().body(service.update(task));
    }

    @DeleteMapping(
        value = "/{id}"
    )
    @Override
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
