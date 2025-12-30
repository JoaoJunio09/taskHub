package com.joaojunio_dev.taskHub.controllers.notification;

import com.joaojunio_dev.taskHub.data.dto.notification.NotificationDTO;
import com.joaojunio_dev.taskHub.mediatype.MediaType;
import com.joaojunio_dev.taskHub.model.Notification;
import com.joaojunio_dev.taskHub.repositories.NotificationRepository;
import com.joaojunio_dev.taskHub.services.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    @Autowired
    private NotificationService service;

    @PostMapping(
        produces = {
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML
        },
        consumes = {
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML
        }
    )
    public ResponseEntity<NotificationDTO> create(@RequestBody NotificationDTO notification) {
        return ResponseEntity.ok().body(service.create(notification));
    }
}