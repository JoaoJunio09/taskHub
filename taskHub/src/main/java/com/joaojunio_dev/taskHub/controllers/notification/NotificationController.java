package com.joaojunio_dev.taskHub.controllers.notification;

import com.joaojunio_dev.taskHub.data.dto.notification.NotificationDTO;
import com.joaojunio_dev.taskHub.mediatype.MediaTypes;
import com.joaojunio_dev.taskHub.services.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification/v1")
public class NotificationController {

    @Autowired
    private NotificationService service;

    @PostMapping(
        produces = {
            MediaTypes.APPLICATION_JSON,
            MediaTypes.APPLICATION_XML,
            MediaTypes.APPLICATION_YAML
        },
        consumes = {
            MediaTypes.APPLICATION_JSON,
            MediaTypes.APPLICATION_XML,
            MediaTypes.APPLICATION_YAML
        }
    )
    public ResponseEntity<NotificationDTO> create(@RequestBody NotificationDTO notification) {
        return ResponseEntity.ok().body(service.create(notification));
    }
}