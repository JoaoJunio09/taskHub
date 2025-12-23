package com.joaojunio_dev.taskHub.repositories;

import com.joaojunio_dev.taskHub.data.dto.TaskDTO;
import com.joaojunio_dev.taskHub.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("")
    List<TaskDTO> findByPersonId(@Param("personId") Long personId);

    @Query("")
    List<TaskDTO> findByCompleted(@Param("completed") boolean completed);

    @Query("")
    List<TaskDTO> findByDate(@Param("date") LocalDate date);
}
