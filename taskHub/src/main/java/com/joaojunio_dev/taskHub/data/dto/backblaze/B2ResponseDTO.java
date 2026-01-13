package com.joaojunio_dev.taskHub.data.dto.backblaze;

public class B2ResponseDTO {

    private String fileName;
    private String contentType;
    private Long size;
    private String fileId;
    private Long personId;

    public B2ResponseDTO() {}

    public B2ResponseDTO(String fileName, String contentType, Long size, String fileId, Long personId) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.size = size;
        this.fileId = fileId;
        this.personId = personId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
