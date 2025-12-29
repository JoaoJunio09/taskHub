package com.joaojunio_dev.taskHub.data.dto.notification;

import com.joaojunio_dev.taskHub.data.dto.TaskDTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class NotificationDTO {

    private Long id;
    private String message;
    private LocalDateTime scheduledAt;
    private Boolean send = false;
    private TaskDTO task;

    public NotificationDTO() {}

    public NotificationDTO(Long id, String message, LocalDateTime scheduledAt, TaskDTO task) {
        this.id = id;
        this.message = message;
        this.scheduledAt = scheduledAt;
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public Boolean getSend() {
        return send;
    }

    public void setSend(Boolean send) {
        this.send = send;
    }

    public TaskDTO getTask() {
        return task;
    }

    public void setTask(TaskDTO task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        NotificationDTO that = (NotificationDTO) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
