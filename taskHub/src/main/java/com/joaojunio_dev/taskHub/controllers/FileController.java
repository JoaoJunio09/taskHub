package com.joaojunio_dev.taskHub.controllers;

import com.joaojunio_dev.taskHub.data.dto.file.UploadResponseDTO;
import com.joaojunio_dev.taskHub.services.file.FileStorageService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/file/v1")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class.getName());

    @Autowired
    private FileStorageService service;

    @PostMapping("/uploadFile")
    public UploadResponseDTO upload(@RequestParam("file") MultipartFile file) {
        var fileName = service.storeFile(file);

        var fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/api/file/v1/downloadFile/")
            .path(fileName)
            .toUriString();
        return new UploadResponseDTO(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }


    @PostMapping("/uploadMultipleFiles")
    public List<UploadResponseDTO> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
            .stream()
            .map(file -> upload(file))
            .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName, HttpServletRequest request) {
        Resource resource = service.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }
        catch (Exception e) {
            logger.error("Could not determine file type!");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + resource.getFilename() + "\""
            )
            .body(resource);
    }
}
