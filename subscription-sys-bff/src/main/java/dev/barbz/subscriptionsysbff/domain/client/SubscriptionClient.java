package dev.barbz.subscriptionsysbff.domain.client;

import dev.barbz.subscriptionsysbff.domain.Subscription;

import java.util.List;

/**
 * Definition of the subscription client.
 */
public interface SubscriptionClient {

    /**
     * Request to register a subscription in the system.
     web client configured to consume the subscription system core.*
     * @param subscription subscription to register.
     * @return saved subscription with the field id updated.
     */
    Subscription createSubscription(Subscription subscription);

    /**
     * Request to get a subscription by its id.
     *
     * @param subscriptionId subscription id.
     * @return subscription found.
     */
    Subscription getSubscriptionById(String subscriptionId);

    /**
     * Request to get all registered subscriptions in the system.
     *
     * @return list of subscriptions
     */
    List<Subscription> getAllSubscriptions();

    /**
     * Request to delete the subscription by its id.
     *
     * @param subscriptionId subscription id to delete.
     */
    void deleteSubscription(String subscriptionId);
}
