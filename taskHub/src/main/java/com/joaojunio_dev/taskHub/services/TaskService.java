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
import java.util.List;

@Service
public class TaskService {

    private final Logger logger = LoggerFactory.getLogger(TaskService.class.getName());

    @Autowired
    TaskRepository repository;

    public List<TaskDTO> findAll() {

        logger.info("Finding All Task's");

        var dtos = parseListObjects(repository.findAll(), TaskDTO.class);
        dtos.forEach(this::addHateoas);
        return dtos;
    }

    public TaskDTO findById(Long id) {

        logger.info("Finding one Task by Id");

        var entity = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Not found this ID : " + id));

        var dto = parseObject(entity, TaskDTO.class);
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

        var entity = new Task();
        entity.setTitle(task.getTitle());
        entity.setDescription(task.getDescription());
        entity.setDate(task.getDate());
        entity.setCompleted(false);
        entity.setPerson(task.getPerson());

        var dto = parseObject(repository.save(entity), TaskDTO.class);
        return addHateoas(dto);
    }

    public TaskDTO update(TaskDTO task) {

        logger.info("Updating one Task");

        if (task == null) throw new ObjectIsNullException("Object is null");

        var entity = repository.findById(task.getId())
            .orElseThrow(() -> new NotFoundException("Not found this ID : " + task.getId()));
        entity.setTitle(task.getTitle());
        entity.setDescription(task.getDescription());
        entity.setDate(task.getDate());
        entity.setCompleted(task.getCompleted());
        entity.setPerson(task.getPerson());

        var dto = parseObject(repository.save(entity), TaskDTO.class);
        return addHateoas(dto);
    }

    public void delete(Long id) {

        logger.info("Deleting a one Task");

        var entity = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Not found this ID : " + id));
        repository.delete(entity);
    }

    private TaskDTO addHateoas(TaskDTO dto) {
        dto.add(linkTo(methodOn(TaskController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(TaskController.class).findAll()).withRel("findAll").withType("GET"));
        return dto;
    }
}
