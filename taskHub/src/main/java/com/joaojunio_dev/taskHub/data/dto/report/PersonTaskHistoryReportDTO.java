package com.joaojunio_dev.taskHub.data.dto.report;

import java.time.LocalDate;

public class PersonTaskHistoryReportDTO {

    private Long id;
    private String name;
    private LocalDate birthDate;
    private String phone;
    private String email;
    private String profileImageFileId;

    public PersonTaskHistoryReportDTO(Long id, String name, LocalDate birthDate, String phone, String email, String profileImageFileId) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.profileImageFileId = profileImageFileId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImageFileId() {
        return profileImageFileId;
    }

    public void setProfileImageFileId(String profileImageFileId) {
        this.profileImageFileId = profileImageFileId;
    }
}
