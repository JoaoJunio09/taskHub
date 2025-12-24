package com.joaojunio_dev.taskHub.data.dto;

import com.joaojunio_dev.taskHub.model.Person;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.Objects;

public class TaskDTO extends RepresentationModel<TaskDTO> {

    private Long id;
    private String title;
    private String description;
    private Date date;
    private Boolean completed;
    private Person person;

    public TaskDTO() {}

    public TaskDTO(Boolean completed, Date date, String description, String title, Long id) {
        this.completed = completed;
        this.date = date;
        this.description = description;
        this.title = title;
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
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

        TaskDTO task = (TaskDTO) o;
        return Objects.equals(getId(), task.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
