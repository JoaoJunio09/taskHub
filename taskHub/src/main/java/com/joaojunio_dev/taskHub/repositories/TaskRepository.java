package com.joaojunio_dev.taskHub.repositories;

import com.joaojunio_dev.taskHub.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.person.id =:personId")
    List<Task> findByPersonId(@Param("personId") Long personId);

    @Query("")
    List<Task> findByCompleted(@Param("completed") boolean completed);

    @Query("")
    List<Task> findByDate(@Param("date") LocalDate date);
}
