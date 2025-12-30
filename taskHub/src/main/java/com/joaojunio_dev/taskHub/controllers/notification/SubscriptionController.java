package com.joaojunio_dev.taskHub.controllers.notification;

import com.joaojunio_dev.taskHub.data.dto.notification.SubscriptionDTO;
import com.joaojunio_dev.taskHub.mediatype.MediaType;
import com.joaojunio_dev.taskHub.model.PushSubscription;
import com.joaojunio_dev.taskHub.repositories.PushSubscriptionRepository;
import com.joaojunio_dev.taskHub.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subscription")
public class SubscriptionController {

    @Autowired
    private PushSubscriptionRepository repository;

    @Autowired
    private PersonService personService;

    @PostMapping(
        consumes = {
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML
        },
        produces = {
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML
        }
    )
    public void save(@RequestBody SubscriptionDTO subscription) {

        var person = personService.findEntityById(subscription.personId());

        PushSubscription sub = new PushSubscription();
        sub.setEndpoint(subscription.endpoint());
        sub.setP256dh(subscription.p256dh());
        sub.setAuth(subscription.auth());
        sub.setPerson(person);
        repository.save(sub);
    }
}
