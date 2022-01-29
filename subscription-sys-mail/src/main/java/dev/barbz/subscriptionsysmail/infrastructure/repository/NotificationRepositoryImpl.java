package dev.barbz.subscriptionsysmail.infrastructure.repository;

import dev.barbz.subscriptionsysmail.domain.Notification;
import dev.barbz.subscriptionsysmail.domain.repository.NotificationRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public record NotificationRepositoryImpl(NotificationMongoRepository repository) implements NotificationRepository {

    @Override
    public Optional<Notification> findByCampaign(String campaign) {
        return repository.findNotificationByCampaign(campaign);
    }
}
