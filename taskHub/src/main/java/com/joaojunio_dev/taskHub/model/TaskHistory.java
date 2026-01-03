package com.joaojunio_dev.taskHub.model;

import com.joaojunio_dev.taskHub.model.enums.TypeOfMovimentInTask;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_task_history")
public class TaskHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private TypeOfMovimentInTask type;

    @Column(nullable = false)
    private LocalDateTime occuredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    public TaskHistory() {}

    public TaskHistory(Long id, TypeOfMovimentInTask type, LocalDateTime occuredAt, Task task, Person person) {
        this.id = id;
        this.type = type;
        this.occuredAt = occuredAt;
        this.task = task;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeOfMovimentInTask getType() {
        return type;
    }

    public void setType(TypeOfMovimentInTask type) {
        this.type = type;
    }

    public LocalDateTime getOccuredAt() {
        return occuredAt;
    }

    public void setOccuredAt(LocalDateTime occuredAt) {
        this.occuredAt = occuredAt;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TaskHistory that = (TaskHistory) o;
        return Objects.equals(getId(), that.getId()) && getType() == that.getType() && Objects.equals(getOccuredAt(), that.getOccuredAt()) && Objects.equals(getTask(), that.getTask()) && Objects.equals(getPerson(), that.getPerson());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getType());
        result = 31 * result + Objects.hashCode(getOccuredAt());
        result = 31 * result + Objects.hashCode(getTask());
        result = 31 * result + Objects.hashCode(getPerson());
        return result;
    }
}
