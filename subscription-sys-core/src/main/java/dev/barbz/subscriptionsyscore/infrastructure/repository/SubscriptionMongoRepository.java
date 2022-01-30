package dev.barbz.subscriptionsyscore.infrastructure.repository;

import dev.barbz.subscriptionsyscore.domain.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Mongo repository layer.
 */
@Repository
public interface SubscriptionMongoRepository extends MongoRepository<Subscription, String> {
}
