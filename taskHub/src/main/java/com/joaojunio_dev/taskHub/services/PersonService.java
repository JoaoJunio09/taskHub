package com.joaojunio_dev.taskHub.services;

import com.joaojunio_dev.taskHub.controllers.PersonController;
import com.joaojunio_dev.taskHub.data.dto.PersonDTO;
import com.joaojunio_dev.taskHub.exceptions.NotFoundException;
import com.joaojunio_dev.taskHub.exceptions.ObjectIsNullException;
import com.joaojunio_dev.taskHub.model.Person;
import com.joaojunio_dev.taskHub.repositories.PersonRepository;
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

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll() {
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
        var entity = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Not found this ID : " + id));
        var dto = parseObject(entity, PersonDTO.class);
        return addHateoas(dto);
    }

    public PersonDTO create(PersonDTO dto) {
        if (dto == null) {
            throw new ObjectIsNullException("The Object is null");
        }

        var entity = parseObject(dto, Person.class);
        dto = parseObject(repository.save(entity), PersonDTO.class);
        return addHateoas(dto);
    }

    private PersonDTO addHateoas(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        return dto;
    }
}
