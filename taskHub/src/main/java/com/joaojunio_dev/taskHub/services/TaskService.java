package com.joaojunio_dev.taskHub.services;

import com.joaojunio_dev.taskHub.controllers.TaskController;
import com.joaojunio_dev.taskHub.data.dto.TaskDTO;
import com.joaojunio_dev.taskHub.exceptions.NotFoundException;
import com.joaojunio_dev.taskHub.exceptions.ObjectIsNullException;
import com.joaojunio_dev.taskHub.model.Task;
import com.joaojunio_dev.taskHub.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.joaojunio_dev.taskHub.mapper.ObjectMapper.parseObject;
import static com.joaojunio_dev.taskHub.mapper.ObjectMapper.parseListObjects;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final Logger logger = LoggerFactory.getLogger(TaskService.class.getName());

    @Autowired
    TaskRepository repository;

    @Autowired
    private PersonService personService;

    public List<TaskDTO> findAll() {

        logger.info("Finding All Task's");

        var dtos = repository.findAll()
            .stream()
            .map(this::convertEntityToDTO)
            .toList();
        dtos.forEach(this::addHateoas);
        return dtos;
    }

    public TaskDTO findById(Long id) {

        logger.info("Finding one Task by Id");

        var entity = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Not found this ID : " + id));

        var dto = convertEntityToDTO(entity);
        return addHateoas(dto);
    }

    public List<TaskDTO> findByPersonId(Long personId) {

        logger.info("Finding Task's by Person Id");

        return null;
    }

    public List<TaskDTO> findByCompleted(Boolean completed) {

        logger.info("Finding Task's by Task Completed");

        return null;
    }

    public List<TaskDTO> findByDate(LocalDate date) {

        logger.info("Finding Task's by Date");

        return null;
    }

    public TaskDTO create(TaskDTO task) {

        logger.info("Creating one Task");

        if (task == null) throw new ObjectIsNullException("Object is null");

        var person = personService.findEntityById(task.getPersonId());
        var entity = new Task();
        entity.setTitle(task.getTitle());
        entity.setDescription(task.getDescription());
        entity.setDate(LocalDateTime.now());
        entity.setCompleted(false);
        entity.setPerson(person);

        var dto = convertEntityToDTO(entity);
        return addHateoas(dto);
    }

    public TaskDTO update(TaskDTO task) {

        logger.info("Updating one Task");

        if (task == null) throw new ObjectIsNullException("Object is null");

        var person = personService.findEntityById(task.getPersonId());
        var entity = repository.findById(task.getId())
            .orElseThrow(() -> new NotFoundException("Not found this ID : " + task.getId()));
        entity.setTitle(task.getTitle());
        entity.setDescription(task.getDescription());
        entity.setCompleted(task.getCompleted());
        entity.setPerson(person);

        var dto = convertEntityToDTO(entity);
        return addHateoas(dto);
    }

    public void delete(Long id) {

        logger.info("Deleting a one Task");

        var entity = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Not found this ID : " + id));
        repository.delete(entity);
    }

    private TaskDTO convertEntityToDTO(Task entity) {
       return new TaskDTO(
            entity.getId(),
            entity.getTitle(),
            entity.getDescription(),
            entity.getDate(),
            entity.getCompleted(),
            entity.getPerson().getId()
        );
    }

    private TaskDTO addHateoas(TaskDTO dto) {
        dto.add(linkTo(methodOn(TaskController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(TaskController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(TaskController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(TaskController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(TaskController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
        return dto;
    }
}
