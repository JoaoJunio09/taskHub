package com.joaojunio_dev.taskHub.controllers;

import com.joaojunio_dev.taskHub.data.dto.PersonDTO;
import com.joaojunio_dev.taskHub.mediatype.MediaType;
import com.joaojunio_dev.taskHub.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping(
        produces = {
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML })
    public ResponseEntity<List<PersonDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(
        value = "/{id}",
        produces = {
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML })
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping(
        consumes = {
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML },
        produces = {
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML })
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO personDTO) {
        return ResponseEntity.ok().body(service.create(personDTO));
    }

    @PutMapping(
        consumes = {
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML },
        produces = {
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML })
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO personDTO) {
        return ResponseEntity.ok().body(service.update(personDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
