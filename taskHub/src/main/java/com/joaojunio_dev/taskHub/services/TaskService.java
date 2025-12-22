package com.joaojunio_dev.taskHub.services;

import com.joaojunio_dev.taskHub.model.Task;
import com.joaojunio_dev.taskHub.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository repository;

    public List<Task> findAll() {
        return null;
    }
}
