package com.joaojunio_dev.taskHub.controllers;

import com.joaojunio_dev.taskHub.controllers.docs.PersonControllerDocs;
import com.joaojunio_dev.taskHub.data.dto.PersonDTO;
import com.joaojunio_dev.taskHub.data.dto.backblaze.B2ResponseDTO;
import com.joaojunio_dev.taskHub.mediatype.MediaTypes;
import com.joaojunio_dev.taskHub.services.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Person")
@RestController
@RequestMapping("/api/person/v1")
public class PersonController implements PersonControllerDocs {

    @Autowired
    private PersonService service;

    @GetMapping(
        produces = {
            MediaTypes.APPLICATION_JSON,
            MediaTypes.APPLICATION_XML,
            MediaTypes.APPLICATION_YAML })
    @Override
    public ResponseEntity<List<PersonDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(
        value = "/{id}",
        produces = {
            MediaTypes.APPLICATION_JSON,
            MediaTypes.APPLICATION_XML,
            MediaTypes.APPLICATION_YAML})
    @Override
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping(
        value = "/uploadProfileImage",
        produces = {
            MediaTypes.APPLICATION_JSON,
            MediaTypes.APPLICATION_XML,
            MediaTypes.APPLICATION_YAML })
    public ResponseEntity<?> uploadProfileImage(@RequestParam("image")MultipartFile image) {
        return null;
    }

    @PostMapping(
        consumes = {
            MediaTypes.APPLICATION_JSON,
            MediaTypes.APPLICATION_XML,
            MediaTypes.APPLICATION_YAML},
        produces = {
            MediaTypes.APPLICATION_JSON,
            MediaTypes.APPLICATION_XML,
            MediaTypes.APPLICATION_YAML})
    @Override
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO personDTO) {
        return ResponseEntity.ok().body(service.create(personDTO));
    }

    @PutMapping(
        consumes = {
            MediaTypes.APPLICATION_JSON,
            MediaTypes.APPLICATION_XML,
            MediaTypes.APPLICATION_YAML},
        produces = {
            MediaTypes.APPLICATION_JSON,
            MediaTypes.APPLICATION_XML,
            MediaTypes.APPLICATION_YAML})
    @Override
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO personDTO) {
        return ResponseEntity.ok().body(service.update(personDTO));
    }

    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
