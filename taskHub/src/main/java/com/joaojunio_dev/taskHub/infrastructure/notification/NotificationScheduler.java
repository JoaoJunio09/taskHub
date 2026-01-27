package com.joaojunio_dev.taskHub.infrastructure.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificationScheduler {

    @Autowired
    private NotificationService service;

    @Scheduled(fixedRate = 6000)
    void run() {
        service.processPendingNotifications();
    }
}
