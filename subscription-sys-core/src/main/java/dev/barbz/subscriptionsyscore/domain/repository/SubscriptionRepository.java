package dev.barbz.subscriptionsyscore.domain.repository;

import dev.barbz.subscriptionsyscore.domain.Subscription;

import java.util.List;
import java.util.Optional;

/**
 * Definition of the subscription repository contract
 */
public interface SubscriptionRepository {

    /**
     * Save an instance of subscription in the db.
     *
     * @param subscription subscription object to save.
     * @return subscription updated.
     */
    Subscription save(Subscription subscription);

    /**
     * Find a subscription by its id
     *
     * @param subscriptionId subscription id
     * @return subscription requested
     */
    Optional<Subscription> findById(String subscriptionId);

    /**
     * Find all subscriptions.
     *
     * @return list of subscription.
     */
    List<Subscription> findAll();

    /**
     * Delete a subscription from the database.
     *
     * @param subscription subscription to delete.
     */
    void delete(Subscription subscription);
}
