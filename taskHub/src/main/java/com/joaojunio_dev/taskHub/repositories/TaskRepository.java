package com.joaojunio_dev.taskHub.repositories;

import com.joaojunio_dev.taskHub.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
