package dev.barbz.subscriptionsysmail.infrastructure.repository;

import dev.barbz.subscriptionsysmail.domain.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Notification mongo repository
 */
public interface NotificationMongoRepository extends MongoRepository<Notification, String> {

    /**
     * Find the email template notification by its campaign.
     *
     * @param campaign campaign to find
     * @return notification mail template
     */
    Optional<Notification> findNotificationByCampaign(String campaign);
}
