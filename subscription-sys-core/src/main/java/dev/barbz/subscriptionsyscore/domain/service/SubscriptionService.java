package dev.barbz.subscriptionsyscore.domain.service;

import dev.barbz.subscriptionsyscore.application.request.CreateSubscriptionRequest;
import dev.barbz.subscriptionsyscore.application.response.SubscriptionResponse;

import java.util.List;

/**
 * Definition of the subscription service contract
 */
public interface SubscriptionService {

    /**
     * Register a new subscription in the system, once it is saved in the database, this function will send a message
     * to the queue to after the saving, the mail service will send a mail to the user subscribed.
     *
     * @param createSubscription subscription to register
     * @return subscription response.
     */
    SubscriptionResponse create(CreateSubscriptionRequest createSubscription);

    /**
     * Retrieve all the subscriptions registered in the system.
     *
     * @return list of subscriptions.
     */
    List<SubscriptionResponse> retrieveAll();

    /**
     * Retrieve a subscription by its id.
     *
     * @param id subscription requested id.
     * @return found subscription.
     */
    SubscriptionResponse retrieveById(String id);

    /**
     * Delete a subscription from the system by its id.
     *
     * @param id subscription id
     */
    void delete(String id);
}
