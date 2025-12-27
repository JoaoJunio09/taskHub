package com.joaojunio_dev.taskHub.repositories;

import com.joaojunio_dev.taskHub.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.person.id =:personId")
    List<Task> findByPersonId(@Param("personId") Long personId);

    @Query("SELECT t FROM Task t WHERE t.completed =:completed")
    List<Task> findByCompleted(@Param("completed") Boolean completed);

    @Query("SELECT t FROM Task t WHERE t.date >= :start AND t.date < :end")
    List<Task> findByDate(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT t FROM Task t WHERE t.date <= :start")
    List<Task> findByDateBefore(@Param("start") LocalDateTime start);

    @Query("SELECT t FROM Task t WHERE t.date >= :end")
    List<Task> findByDateAfter(@Param("end") LocalDateTime end);
}
