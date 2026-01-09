package com.joaojunio_dev.taskHub.services;

import com.joaojunio_dev.taskHub.exceptions.ObjectIsNullException;
import com.joaojunio_dev.taskHub.file.exporter.contract.TaskHistoryExporter;
import com.joaojunio_dev.taskHub.file.exporter.factory.FileExporterFactory;
import com.joaojunio_dev.taskHub.model.TaskHistory;
import com.joaojunio_dev.taskHub.repositories.TaskHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskHistoryService {

    private static final Logger logger = LoggerFactory.getLogger(TaskHistoryService.class.getName());

    @Autowired
    FileExporterFactory exporter;

    @Autowired
    private TaskHistoryRepository repository;

    public List<TaskHistory> findAll() {
        return repository.findAll();
    }

    public List<TaskHistory> findByPersonId(Long id) {
        return repository.findByPersonId(id);
    }

    public Resource export(String acceptHeader) {

        logger.info("Exporting a Task's History");

        try {
            TaskHistoryExporter exporter = this.exporter.getExporter(acceptHeader);
            return exporter.exportTasks(findAll());
        } catch (Exception e) {
            throw new RuntimeException("Error during file export", e);
        }
    }

    public Resource exportByPersonId(String acceptHeader, Long personId) {

        logger.info("Exporting a Task's History by Person ID");

        try {
            TaskHistoryExporter exporter = this.exporter.getExporter(acceptHeader);

            var tasks = findByPersonId(personId);
            return exporter.exportTasksByPersonId(tasks);
        } catch (Exception e) {
            throw new RuntimeException("Error during file export", e);
        }
    }

    public void register(TaskHistory taskHistory) {
        if (taskHistory == null) throw new ObjectIsNullException("Object is null");

        logger.info("Registering the task history in the database");
        repository.save(taskHistory);
    }
}
