package com.joaojunio_dev.taskHub.services;

import com.joaojunio_dev.taskHub.data.dto.TaskHistoryDTO;
import com.joaojunio_dev.taskHub.exceptions.ObjectIsNullException;
import com.joaojunio_dev.taskHub.model.TaskHistory;
import com.joaojunio_dev.taskHub.repositories.TaskHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.joaojunio_dev.taskHub.mapper.ObjectMapper.parseObject;

@Component
public class RecordingTaskHistory {

    private static final Logger logger = LoggerFactory.getLogger(RecordingTaskHistory.class.getName());

    @Autowired
    private TaskHistoryRepository repository;

    public void register(TaskHistoryDTO taskHistory) {
        if (taskHistory == null) throw new ObjectIsNullException("Object was null");

        logger.info("Registering the task history in the database");
        repository.save(parseObject(taskHistory, TaskHistory.class));
    }
}
