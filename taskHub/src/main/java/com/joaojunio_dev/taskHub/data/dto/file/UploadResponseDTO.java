package com.joaojunio_dev.taskHub.data.dto.file;

public class UploadResponseDTO {

    private String fileName;
    private String fileDownlodUri;
    private String fileType;
    private Long size;

    public UploadResponseDTO() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownlodUri() {
        return fileDownlodUri;
    }

    public void setFileDownlodUri(String fileDownlodUri) {
        this.fileDownlodUri = fileDownlodUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public UploadResponseDTO(String fileName, String fileDownlodUri, String fileType, Long size) {
        this.fileName = fileName;
        this.fileDownlodUri = fileDownlodUri;
        this.fileType = fileType;
        this.size = size;
    }
}
