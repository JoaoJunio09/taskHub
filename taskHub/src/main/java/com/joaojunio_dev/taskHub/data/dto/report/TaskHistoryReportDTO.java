package com.joaojunio_dev.taskHub.data.dto.report;

import java.time.LocalDateTime;

public class TaskHistoryReportDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime occurredAt;
    private Integer action;

    public TaskHistoryReportDTO(Long id, String title, String description, LocalDateTime occurredAt, Integer action) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.occurredAt = occurredAt;
        this.action = action;
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

    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(LocalDateTime occurredAt) {
        this.occurredAt = occurredAt;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }
}
