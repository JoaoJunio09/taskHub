package com.joaojunio_dev.taskHub.controllers.notification;

import com.joaojunio_dev.taskHub.data.dto.notification.SubscriptionDTO;
import com.joaojunio_dev.taskHub.model.PushSubscription;
import com.joaojunio_dev.taskHub.repositories.PushSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    private PushSubscriptionRepository repository;

    public void save(@RequestBody SubscriptionDTO subscription) {
        PushSubscription sub = new PushSubscription();
        sub.setEndpoint(subscription.endpoint());
        sub.setP256dh(subscription.p256dh());
        sub.setAuth(subscription.auth());
        repository.save(sub);
    }
}
