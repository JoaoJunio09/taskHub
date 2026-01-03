package com.joaojunio_dev.taskHub.data.dto;

import com.joaojunio_dev.taskHub.model.Person;
import com.joaojunio_dev.taskHub.model.Task;
import com.joaojunio_dev.taskHub.model.User;
import com.joaojunio_dev.taskHub.model.enums.TypeOfMovimentInTask;

import java.time.LocalDateTime;
import java.util.Objects;

public class TaskHistoryDTO {

    private Long id;
    private TypeOfMovimentInTask type;
    private LocalDateTime occuredAt;
    private TaskDTO task;
    private PersonDTO person;

    public TaskHistoryDTO() {}

    public TaskHistoryDTO(Long id, TypeOfMovimentInTask type, LocalDateTime occuredAt, TaskDTO task, PersonDTO person) {
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

    public TaskDTO getTask() {
        return task;
    }

    public void setTask(TaskDTO task) {
        this.task = task;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TaskHistoryDTO that = (TaskHistoryDTO) o;
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
