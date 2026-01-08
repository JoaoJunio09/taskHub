package com.joaojunio_dev.taskHub.repositories;

import com.joaojunio_dev.taskHub.model.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Long> {

    List<TaskHistory> findByPersonId(Long id);
}
