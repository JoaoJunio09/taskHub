package com.joaojunio_dev.taskHub.services;

import com.joaojunio_dev.taskHub.exceptions.ObjectIsNullException;
import com.joaojunio_dev.taskHub.model.TaskHistory;
import com.joaojunio_dev.taskHub.repositories.TaskHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecordingTaskHistory {

    private static final Logger logger = LoggerFactory.getLogger(RecordingTaskHistory.class.getName());

    @Autowired
    private TaskHistoryRepository repository;

    public List<TaskHistory> findAll() {
        return repository.findAll();
    }

    public void register(TaskHistory taskHistory) {
        if (taskHistory == null) throw new ObjectIsNullException("Object is null");

        logger.info("Registering the task history in the database");
        repository.save(taskHistory);
    }
}
