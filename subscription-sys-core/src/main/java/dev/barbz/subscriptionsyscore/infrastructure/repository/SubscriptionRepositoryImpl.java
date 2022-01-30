package dev.barbz.subscriptionsyscore.infrastructure.repository;

import dev.barbz.subscriptionsyscore.domain.Subscription;
import dev.barbz.subscriptionsyscore.domain.repository.SubscriptionRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Subscription repository implementation of the contract.
 */
@Component
public class SubscriptionRepositoryImpl implements SubscriptionRepository {

    private final SubscriptionMongoRepository repository;

    /**
     * Subscription repository implementation constructor
     *
     * @param repository mongo repository
     */
    public SubscriptionRepositoryImpl(SubscriptionMongoRepository repository) {
        this.repository = repository;
    }

    /**
     * Save an instance of subscription in the db.
     *
     * @param subscription subscription object to save.
     * @return subscription updated.
     */
    @Override
    public Subscription save(Subscription subscription) {
        return repository.save(subscription);
    }

    /**
     * Find a subscription by its id
     *
     * @param subscriptionId subscription id
     * @return subscription requested
     */
    @Override
    public Optional<Subscription> findById(String subscriptionId) {
        return repository.findById(subscriptionId);
    }

    /**
     * Find all subscriptions.
     *
     * @return list of subscription.
     */
    @Override
    public List<Subscription> findAll() {
        return repository.findAll();
    }

    /**
     * Delete a subscription from the database.
     *
     * @param subscription subscription to delete.
     */
    @Override
    public void delete(Subscription subscription) {
        repository.delete(subscription);
    }
}
