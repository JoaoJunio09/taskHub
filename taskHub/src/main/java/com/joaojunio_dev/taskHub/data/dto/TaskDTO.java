package com.joaojunio_dev.taskHub.data.dto;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Objects;

public class TaskDTO extends RepresentationModel<TaskDTO> {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime date;
    private Boolean completed;
    private Long personId;

    public TaskDTO() {}

    public TaskDTO(Long id, String title, String description, LocalDateTime date, Boolean completed, Long personId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.completed = completed;
        this.personId = personId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
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

        TaskDTO task = (TaskDTO) o;
        return Objects.equals(getId(), task.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
