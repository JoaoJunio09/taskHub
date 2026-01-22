package com.joaojunio_dev.taskHub.services;

import com.joaojunio_dev.taskHub.data.dto.TaskHistoryDTO;
import com.joaojunio_dev.taskHub.data.dto.report.PersonTaskHistoryReportDTO;
import com.joaojunio_dev.taskHub.data.dto.report.TaskHistoryReportDTO;
import com.joaojunio_dev.taskHub.exceptions.FileStorageException;
import com.joaojunio_dev.taskHub.exceptions.ObjectIsNullException;
import com.joaojunio_dev.taskHub.exporter.contract.TaskHistoryExporter;
import com.joaojunio_dev.taskHub.exporter.factory.FileExporterFactory;
import com.joaojunio_dev.taskHub.model.Person;
import com.joaojunio_dev.taskHub.model.TaskHistory;
import com.joaojunio_dev.taskHub.model.enums.TypeOfMovimentInTask;
import com.joaojunio_dev.taskHub.repositories.TaskHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskHistoryService {

    private static final Logger logger = LoggerFactory.getLogger(TaskHistoryService.class.getName());

    @Autowired
    FileExporterFactory exporter;

    @Autowired
    private TaskHistoryRepository repository;

    @Autowired
    private PersonService personService;

    public List<TaskHistoryDTO> findByPersonId(Long personId) {
        return repository.findByPersonId(personId)
            .stream()
            .map(this::convertTaskHistoryEntityToDto)
            .toList();
    }

    public Resource export(String acceptHeader) {

        logger.info("Exporting a Task's History");

        try {
            TaskHistoryExporter exporter = this.exporter.getExporter(acceptHeader);
            var list = repository.findAll();
            return exporter.exportTasks(list);
        } catch (Exception e) {
            throw new RuntimeException("Error during file export", e);
        }
    }

    public Resource exportByPersonId(String acceptHeader, Long personId, String routeGetProfileImage) {

        logger.info("Exporting a Task's History by Person ID");

        try {
            TaskHistoryExporter exporter = this.exporter.getExporter(acceptHeader);

            var tasksEntities = repository.findByPersonId(personId);
            var tasks = convertTaskHistoryEntityToReportDto(tasksEntities);
            var personEntity = personService.findEntityById(personId);
            var person = convertPersonEntityToDto(personEntity);

            if (tasks.isEmpty()) throw new FileStorageException("Invalid: Tasks is empty or Person is null!");

            return exporter.exportTasksByPersonId(tasks, person, routeGetProfileImage);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error during file export", e);
        }
    }

    public void register(TaskHistory taskHistory) {
        if (taskHistory == null) throw new ObjectIsNullException("Object is null");

        logger.info("Registering the task history in the database");
        repository.save(taskHistory);
    }

    private PersonTaskHistoryReportDTO convertPersonEntityToDto(Person entity) {
        return new PersonTaskHistoryReportDTO(
            entity.getId(),
            entity.getFirstName() + " " + entity.getLastName(),
            entity.getBirthDate(),
            entity.getPhone(),
            entity.getEmail(),
            entity.getProfileImageFileId()
        );
    }

    private List<TaskHistoryReportDTO> convertTaskHistoryEntityToReportDto(List<TaskHistory> tasksHistory) {
        List<TaskHistoryReportDTO> tasks = new ArrayList<>();
        for (TaskHistory task : tasksHistory) {
            tasks.add(new TaskHistoryReportDTO(
                task.getId(),
                task.getTask().getTitle(),
                task.getTask().getDescription(),
                task.getOccurredAt(),
                task.getType()));
        }
        return tasks;
    }

    private TaskHistoryDTO convertTaskHistoryEntityToDto(TaskHistory task) {
        return new TaskHistoryDTO(
            task.getId(),
            task.getType(),
            task.getOccurredAt(),
            task.getTask().getTitle(),
            task.getTask().getDescription(),
            task.getPerson().getId());
    }
}
