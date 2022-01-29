package dev.barbz.subscriptionsysmail.domain.repository;

import dev.barbz.subscriptionsysmail.domain.Notification;

import java.util.Optional;

public interface NotificationRepository {

    Optional<Notification> findByCampaign(String campaign);
}
