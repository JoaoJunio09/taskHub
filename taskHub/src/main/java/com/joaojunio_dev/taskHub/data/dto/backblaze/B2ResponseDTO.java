package com.joaojunio_dev.taskHub.data.dto.backblaze;

public class B2ResponseDTO {

    private String filName;
    private String fileVersion;
    private String contentType;
    private Long size;
    private String fileId;
    private Long personId;

    public B2ResponseDTO() {}

    public B2ResponseDTO(String filName, String fileVersion, String contentType, Long size, String fileId, Long personId) {
        this.filName = filName;
        this.fileVersion = fileVersion;
        this.contentType = contentType;
        this.size = size;
        this.fileId = fileId;
        this.personId = personId;
    }

    public String getFilName() {
        return filName;
    }

    public void setFilName(String filName) {
        this.filName = filName;
    }

    public String getFileVersion() {
        return fileVersion;
    }

    public void setFileVersion(String fileVersion) {
        this.fileVersion = fileVersion;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }
}
