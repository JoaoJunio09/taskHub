package com.joaojunio_dev.taskHub.repositories;

import com.joaojunio_dev.taskHub.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Buscar as notificações passadas, ou seja, que ja se passaram pela data atual.
    List<Notification> findBySendFalseAndScheduledAtBefore(LocalDateTime now);
}
