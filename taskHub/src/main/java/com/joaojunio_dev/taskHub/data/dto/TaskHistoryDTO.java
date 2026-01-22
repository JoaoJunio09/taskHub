package com.joaojunio_dev.taskHub.data.dto;

import com.joaojunio_dev.taskHub.model.enums.TypeOfMovimentInTask;

import java.time.LocalDateTime;
import java.util.Objects;

public class TaskHistoryDTO {

    private Long id;
    private Integer type;
    private LocalDateTime occurredAt;
    private String taskTitle;
    private String taskDescription;
    private Long personId;

    public TaskHistoryDTO() {}

    public TaskHistoryDTO(Long id, Integer type, LocalDateTime occurredAt, String taskTitle, String taskDescription, Long personId) {
        this.id = id;
        this.type = type;
        this.occurredAt = occurredAt;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.personId = personId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(LocalDateTime occurredAt) {
        this.occurredAt = occurredAt;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TaskHistoryDTO that = (TaskHistoryDTO) o;
        return getType() == that.getType() && Objects.equals(getOccurredAt(), that.getOccurredAt()) && Objects.equals(getTaskTitle(), that.getTaskTitle()) && Objects.equals(getTaskDescription(), that.getTaskDescription()) && Objects.equals(getPersonId(), that.getPersonId());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getType());
        result = 31 * result + Objects.hashCode(getOccurredAt());
        result = 31 * result + Objects.hashCode(getTaskTitle());
        result = 31 * result + Objects.hashCode(getTaskDescription());
        result = 31 * result + Objects.hashCode(getPersonId());
        return result;
    }
}
