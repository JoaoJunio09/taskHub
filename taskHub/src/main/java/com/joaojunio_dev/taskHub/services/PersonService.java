package com.joaojunio_dev.taskHub.services;

import com.joaojunio_dev.taskHub.controllers.PersonController;
import com.joaojunio_dev.taskHub.data.dto.PersonDTO;
import com.joaojunio_dev.taskHub.exceptions.NotFoundException;
import com.joaojunio_dev.taskHub.exceptions.ObjectIsNullException;
import com.joaojunio_dev.taskHub.model.Person;
import com.joaojunio_dev.taskHub.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.joaojunio_dev.taskHub.mapper.ObjectMapper.parseObject;
import static com.joaojunio_dev.taskHub.mapper.ObjectMapper.parseListObjects;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private final Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll() {

        logger.info("Find's All Person");

        var entities = repository.findAll();
        List<PersonDTO> dtos = new ArrayList<>();
        parseListObjects(entities, PersonDTO.class).stream()
            .map(dto -> {
                addHateoas(dto);
                dtos.add(dto);
                return dto;
            });
        return dtos;
    }

    public PersonDTO findById(Long id) {

        logger.info("Find a one Person");

        var entity = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Not found this ID : " + id));
        var dto = parseObject(entity, PersonDTO.class);
        return addHateoas(dto);
    }

    public PersonDTO create(PersonDTO dto) {

        logger.info("Creating a one Person");

        if (dto == null) {
            throw new ObjectIsNullException("The Object is null");
        }

        var entity = parseObject(dto, Person.class);
        dto = parseObject(repository.save(entity), PersonDTO.class);
        return addHateoas(dto);
    }

    public PersonDTO update(PersonDTO dto) {

        logger.info("Updating a exists Person");

        var entity = repository.findById(dto.getId())
            .orElseThrow(() -> new NotFoundException("Not found this ID:" + dto.getId()));
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setBirthDate(dto.getBirthDate());
        entity.setPhone(dto.getPhone());
        var person = parseObject(repository.save(entity), PersonDTO.class);
        return addHateoas(person);
    }

    public void delete(Long id) {

        logger.info("Deleting a one Person");

        var entity = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Not found this ID:" + id));
        repository.delete(entity);
    }

    private PersonDTO addHateoas(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
        return dto;
    }
}
