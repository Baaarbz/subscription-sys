package dev.barbz.subscriptionsysmail.infrastructure.repository;

import dev.barbz.subscriptionsysmail.domain.Notification;
import dev.barbz.subscriptionsysmail.domain.repository.NotificationRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of the notification repository contract.
 */
@Component
public class NotificationRepositoryImpl implements NotificationRepository {

    private final NotificationMongoRepository repository;

    /**
     * Notification repository constructor.
     *
     * @param repository notification mongo repository.
     */
    public NotificationRepositoryImpl(NotificationMongoRepository repository) {
        this.repository = repository;
    }

    /**
     * Find by campaign the mail template to send the notification.
     *
     * @param campaign campaign to find
     * @return notification mail template
     */
    @Override
    public Optional<Notification> findByCampaign(String campaign) {
        return repository.findNotificationByCampaign(campaign);
    }
}
