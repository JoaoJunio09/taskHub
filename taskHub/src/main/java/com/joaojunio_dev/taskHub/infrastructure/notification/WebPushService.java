package com.joaojunio_dev.taskHub.infrastructure.notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaojunio_dev.taskHub.data.dto.notification.NotificationPayloadDTO;
import com.joaojunio_dev.taskHub.exceptions.IsNotPossibleSendNotificationException;
import com.joaojunio_dev.taskHub.exceptions.ObjectIsNullException;
import com.joaojunio_dev.taskHub.model.PushSubscription;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebPushService {

    private final static Logger logger = LoggerFactory.getLogger(WebPushService.class.getName());

    @Autowired
    private PushService service;

    public void send(PushSubscription subscription, NotificationPayloadDTO payload) {

        if (subscription == null || payload == null) throw new ObjectIsNullException("Object is null");

        try {
            Subscription sub = new Subscription(
                subscription.getEndpoint(),
                new Subscription.Keys(
                    subscription.getP256dh(),
                    subscription.getAuth()
                )
            );

            String jsonPayload = new ObjectMapper().writeValueAsString(payload);
            Notification notification = new Notification(sub, jsonPayload);

            logger.info("Sending notification!");

            service.send(notification);
        }
        catch (Exception e) {
            throw new IsNotPossibleSendNotificationException("There was an error sending the notification to the client!");
        }
    }
}
