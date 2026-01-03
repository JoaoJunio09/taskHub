package com.joaojunio_dev.taskHub.repositories;

import com.joaojunio_dev.taskHub.model.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Long> {

}
