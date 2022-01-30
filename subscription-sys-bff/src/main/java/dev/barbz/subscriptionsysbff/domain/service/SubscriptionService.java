package dev.barbz.subscriptionsysbff.domain.service;

import dev.barbz.subscriptionsysbff.application.request.RegisterSubscriptionRequest;
import dev.barbz.subscriptionsysbff.application.response.SubscriptionResponse;

import java.util.List;

/**
 * Definition of the subscription service.
 */
public interface SubscriptionService {

    /**
     * Register a subscription in the system. Consuming the core of the subscription system to save and notify
     * the new subscription.
     *
     * @param registerSubscription subscription to register.
     * @return id generated after the saving action.
     */
    String registerSubscription(RegisterSubscriptionRequest registerSubscription);

    /**
     * Retrieve all the subscriptions in the system.
     *
     * @return list of subscriptions
     */
    List<SubscriptionResponse> retrieveAllSubscriptions();

    /**
     * Retrieve a subscription by its id.
     *
     * @param id subscription id
     * @return subscription in case of been found.
     */
    SubscriptionResponse retrieveSubscription(String id);

    /**
     * Cancel de subscription of the user by deleting his data from the system.
     *
     * @param id subscription id to delete.
     */
    void cancelSubscription(String id);
}
