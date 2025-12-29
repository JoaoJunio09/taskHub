package com.joaojunio_dev.taskHub.services.notification;

import com.joaojunio_dev.taskHub.data.dto.notification.NotificationPayloadDTO;
import com.joaojunio_dev.taskHub.model.Notification;
import com.joaojunio_dev.taskHub.model.PushSubscription;
import com.joaojunio_dev.taskHub.repositories.NotificationRepository;
import com.joaojunio_dev.taskHub.repositories.PushSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private final NotificationRepository notificationRepository;

    @Autowired
    private final PushSubscriptionRepository subscriptionRepository;

    @Autowired
    private final WebPushService webPushService;

    public NotificationService(NotificationRepository notificationRepository, PushSubscriptionRepository subscriptionRepository, WebPushService webPushService) {
        this.notificationRepository = notificationRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.webPushService = webPushService;
    }

    public void processPendingNotifications() {
        List<Notification> notifications = 
            notificationRepository.findBySentFalseAndScheduledAtBefore(LocalDateTime.now());
        for (Notification notification : notifications) {

            List<PushSubscription> subscriptions =
                subscriptionRepository.findByPerson(notification.getTask().getPerson());

            for (PushSubscription sub : subscriptions) {
                webPushService.send(
                    sub,
                    new NotificationPayloadDTO(
                        "TaskHub",
                        notification.getMessage()
                    )
                );
            }

            notification.setSend(true);
            notificationRepository.save(notification);
        }
    }
}
