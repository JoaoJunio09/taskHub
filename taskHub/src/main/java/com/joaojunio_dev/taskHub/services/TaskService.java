package com.joaojunio_dev.taskHub.services;

import com.joaojunio_dev.taskHub.data.dto.TaskDTO;
import com.joaojunio_dev.taskHub.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private final Logger logger = LoggerFactory.getLogger(TaskService.class.getName());

    @Autowired
    TaskRepository repository;

    public List<TaskDTO> findAll() {

        logger.info("Finding All Task's");

        return null;
    }

    public TaskDTO findById(Long id) {

        logger.info("Finding one Task by Id");

        return null;
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

        return null;
    }

    public TaskDTO update(TaskDTO task) {

        logger.info("Updating one Task");

        return null;
    }

    public void delete(Long id) {

        logger.info("Deleting a one Task");

    }
}
