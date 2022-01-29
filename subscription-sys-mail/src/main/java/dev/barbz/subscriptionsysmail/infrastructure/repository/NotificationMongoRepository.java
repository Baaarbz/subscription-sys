package dev.barbz.subscriptionsysmail.infrastructure.repository;

import dev.barbz.subscriptionsysmail.domain.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NotificationMongoRepository extends MongoRepository<Notification, String> {

    Optional<Notification> findNotificationByCampaign(String campaign);
}
