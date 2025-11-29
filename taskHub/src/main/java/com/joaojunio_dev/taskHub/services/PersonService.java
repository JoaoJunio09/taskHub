package com.joaojunio_dev.taskHub.services;

import com.joaojunio_dev.taskHub.data.dto.PersonDTO;
import com.joaojunio_dev.taskHub.mapper.ObjectMapper;
import com.joaojunio_dev.taskHub.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.joaojunio_dev.taskHub.mapper.ObjectMapper.parseObject;
import static com.joaojunio_dev.taskHub.mapper.ObjectMapper.parseListObjects;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll() {
        var entities = repository.findAll();
        var dto = parseListObjects(entities, PersonDTO.class);
        return dto;
    }
}
