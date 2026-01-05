package com.joaojunio_dev.taskHub.services;

import com.joaojunio_dev.taskHub.controllers.TaskController;
import com.joaojunio_dev.taskHub.data.dto.TaskDTO;
import com.joaojunio_dev.taskHub.exceptions.InvalidTypeOfDateException;
import com.joaojunio_dev.taskHub.exceptions.NotFoundException;
import com.joaojunio_dev.taskHub.exceptions.ObjectIsNullException;
import com.joaojunio_dev.taskHub.model.Person;
import com.joaojunio_dev.taskHub.model.Task;
import com.joaojunio_dev.taskHub.model.TaskHistory;
import com.joaojunio_dev.taskHub.model.enums.ThisDateOrPreviousOrLater;
import com.joaojunio_dev.taskHub.model.enums.TypeOfMovimentInTask;
import com.joaojunio_dev.taskHub.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private RecordingTaskHistory recordingHistory;

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

        var dtos = repository.findByPersonId(personId)
            .stream()
            .map(this::convertEntityToDTO)
            .toList();
        dtos.forEach(this::addHateoas);
        return dtos;
    }

    public List<TaskDTO> findByCompleted(Boolean completed) {

        logger.info("Finding Task's by Task Completed");

        var dtos = repository.findByCompleted(completed)
            .stream()
            .map(this::convertEntityToDTO)
            .toList();
        dtos.forEach(this::addHateoas);
        return dtos;
    }

    public List<TaskDTO> findByDate(LocalDate date, ThisDateOrPreviousOrLater type) {

        logger.info("Finding Task's by Date");

        List<Task> tasks;

        if (type == ThisDateOrPreviousOrLater.THIS_DATE) {
            tasks = repository.findByDate(date.atStartOfDay(), date.plusDays(1).atStartOfDay());
        } else if (type == ThisDateOrPreviousOrLater.PREVIOUS_DATE) {
            tasks = repository.findByDateBefore(date.atStartOfDay());
        } else if (type == ThisDateOrPreviousOrLater.LATER_DATE) {
            tasks = repository.findByDateAfter(date.plusDays(1).atStartOfDay());
        } else {
            throw new InvalidTypeOfDateException("Invalid type for search date!");
        }

        var dtos = tasks
            .stream()
            .map(this::convertEntityToDTO)
            .toList();
        dtos.forEach(this::addHateoas);
        return dtos;
    }

    public TaskDTO create(TaskDTO task) {

        logger.info("Creating one Task");

        if (task == null) throw new ObjectIsNullException("Object is null");

        var person = personService.findEntityById(task.getPersonId());
        var entity = new Task();
        entity.setId(task.getId());
        entity.setTitle(task.getTitle());
        entity.setDescription(task.getDescription());
        entity.setDate(LocalDateTime.now());
        entity.setCompleted(false);
        entity.setPerson(person);

        var dto = convertEntityToDTO(repository.save(entity));
        registerHistoryTheTask(entity, person, TypeOfMovimentInTask.CREATE);
        return addHateoas(dto);
    }

    public TaskDTO update(TaskDTO task) {

        logger.info("Updating one Task");

        if (task == null) throw new ObjectIsNullException("Object is null");

        var entity = repository.findById(task.getId())
            .orElseThrow(() -> new NotFoundException("Not found this ID : " + task.getId()));
        entity.setTitle(task.getTitle());
        entity.setDescription(task.getDescription());
        entity.setCompleted(task.getCompleted());
        entity.setPerson(entity.getPerson());

        var dto = convertEntityToDTO(repository.save(entity));
        registerHistoryTheTask(entity, entity.getPerson(), TypeOfMovimentInTask.UPDATE);
        return addHateoas(dto);
    }

    public void delete(Long id) {

        logger.info("Deleting a one Task");

        var entity = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Not found this ID : " + id));

        registerHistoryTheTask(entity, entity.getPerson(), TypeOfMovimentInTask.DELETE);
        repository.delete(entity);
    }

    private void registerHistoryTheTask(Task task, Person person, TypeOfMovimentInTask type) {
        recordingHistory.register(new TaskHistory(null, type, LocalDateTime.now(), task, person));
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
