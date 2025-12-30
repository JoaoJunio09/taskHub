package com.joaojunio_dev.taskHub.data.dto.notification;

public record SubscriptionDTO(
        String endpoint,
        String p256dh,
        String auth,
        Long personId
) {}