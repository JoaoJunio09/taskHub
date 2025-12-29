package com.joaojunio_dev.taskHub.repositories;

import com.joaojunio_dev.taskHub.model.Person;
import com.joaojunio_dev.taskHub.model.PushSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PushSubscriptionRepository extends JpaRepository<PushSubscription, Long> {

    List<PushSubscription> findByPerson(Person person);
}
