package dev.barbz.subscriptionsysmail.domain.repository;

import dev.barbz.subscriptionsysmail.domain.Notification;

import java.util.Optional;

/**
 * Definition of the notification repository contract.
 */
public interface NotificationRepository {

    /**
     * Find by campaign the mail template to send the notification.
     *
     * @param campaign campaign to find
     * @return notification mail template
     */
    Optional<Notification> findByCampaign(String campaign);
}
