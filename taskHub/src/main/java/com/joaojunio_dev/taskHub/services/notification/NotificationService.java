package com.joaojunio_dev.taskHub.services.notification;

import com.joaojunio_dev.taskHub.data.dto.TaskDTO;
import com.joaojunio_dev.taskHub.data.dto.notification.NotificationDTO;
import com.joaojunio_dev.taskHub.data.dto.notification.NotificationPayloadDTO;
import com.joaojunio_dev.taskHub.exceptions.ObjectIsNullException;
import com.joaojunio_dev.taskHub.model.Notification;
import com.joaojunio_dev.taskHub.model.Person;
import com.joaojunio_dev.taskHub.model.PushSubscription;
import com.joaojunio_dev.taskHub.model.Task;
import com.joaojunio_dev.taskHub.repositories.NotificationRepository;
import com.joaojunio_dev.taskHub.repositories.PushSubscriptionRepository;
import com.joaojunio_dev.taskHub.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final static Logger logger = LoggerFactory.getLogger(NotificationService.class.getName());

    @Autowired
    private final NotificationRepository notificationRepository;

    @Autowired
    private final PushSubscriptionRepository subscriptionRepository;

    @Autowired
    private final WebPushService webPushService;

    @Autowired
    private final PersonService personService;

    public NotificationService(NotificationRepository notificationRepository, PushSubscriptionRepository subscriptionRepository, WebPushService webPushService, PersonService personService) {
        this.notificationRepository = notificationRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.webPushService = webPushService;
        this.personService = personService;
    }

    public void processPendingNotifications() {
        List<Notification> notifications = 
            notificationRepository.findBySendFalseAndScheduledAtBefore(LocalDateTime.now());
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

    public NotificationDTO create(NotificationDTO notification) {

        logger.info("Creating a one Notofication");

        if (notification == null) throw new ObjectIsNullException("Object is null");

        var person = personService.findEntityById(notification.getTask().getPersonId());

        return convertEntityToDTO(notificationRepository.save(convertDtoToEntity(notification, person)));
    }

    private Notification convertDtoToEntity(NotificationDTO dto, Person person) {
        return new Notification(
            dto.getId(),
            dto.getMessage(),
            dto.getScheduledAt(),
            new Task(
                dto.getTask().getId(),
                dto.getTask().getCompleted(),
                dto.getTask().getDate(),
                dto.getTask().getDescription(),
                dto.getTask().getTitle(),
                person
            )
        );
    }

    private NotificationDTO convertEntityToDTO(Notification entity) {
        return new NotificationDTO(
            entity.getId(),
            entity.getMessage(),
            entity.getScheduledAt(),
            new TaskDTO(
                entity.getTask().getId(),
                entity.getTask().getTitle(),
                entity.getTask().getDescription(),
                entity.getTask().getDate(),
                entity.getTask().getCompleted(),
                entity.getTask().getPerson().getId()
            )
        );
    }
}
