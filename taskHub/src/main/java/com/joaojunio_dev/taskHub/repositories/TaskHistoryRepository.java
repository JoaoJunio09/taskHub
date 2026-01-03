package com.joaojunio_dev.taskHub.repositories;

import com.joaojunio_dev.taskHub.model.TaskHistory;
import com.joaojunio_dev.taskHub.model.User;
import com.joaojunio_dev.taskHub.model.enums.TypeOfMovimentInTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Long> {

    List<TaskHistory> findByUser(User user);
    List<TaskHistory> findByTypeAndUser(TypeOfMovimentInTask type, User user);
}
