package dev.barbz.subscriptionsysbff.domain.service;

import dev.barbz.subscriptionsysbff.application.request.RegisterSubscriptionRequest;
import dev.barbz.subscriptionsysbff.application.response.SubscriptionResponse;
import dev.barbz.subscriptionsysbff.domain.Subscription;
import dev.barbz.subscriptionsysbff.domain.client.SubscriptionClient;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.barbz.subscriptionsysbff.domain.util.SubscriptionUtil.mapToDomainSubscription;
import static dev.barbz.subscriptionsysbff.domain.util.SubscriptionUtil.mapToSubscriptionResponse;
import static dev.barbz.subscriptionsysbff.domain.util.SubscriptionUtil.mapToSubscriptionResponseList;

/**
 * Implementation of the subscription service.
 * Which will consume the service of subscription core to retrieve and treat the data.
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionClient subscriptionClient;

    /**
     * Subscription service constructor.
     *
     * @param subscriptionClient subscription client to realize requests to the core.
     */
    public SubscriptionServiceImpl(SubscriptionClient subscriptionClient) {
        this.subscriptionClient = subscriptionClient;
    }

    /**
     * Register a subscription in the system. Consuming the core of the subscription system to save and notify
     * the new subscription.
     *
     * @param registerSubscription subscription to register.
     * @return id generated after the saving action.
     */
    @Override
    public String registerSubscription(RegisterSubscriptionRequest registerSubscription) {
        Subscription subscription = mapToDomainSubscription(registerSubscription);

        return subscriptionClient.createSubscription(subscription).id();
    }

    /**
     * Retrieve all the subscriptions in the system.
     *
     * @return list of subscriptions
     */
    @Override
    public List<SubscriptionResponse> retrieveAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionClient.getAllSubscriptions();

        return mapToSubscriptionResponseList(subscriptions);
    }

    /**
     * Retrieve a subscription by its id.
     *
     * @param id subscription id
     * @return subscription in case of been found.
     */
    @Override
    public SubscriptionResponse retrieveSubscription(String id) {
        Subscription subscription = subscriptionClient.getSubscriptionById(id);

        return mapToSubscriptionResponse(subscription);
    }

    /**
     * Cancel de subscription of the user by deleting his data from the system.
     *
     * @param id subscription id to delete.
     */
    @Override
    public void cancelSubscription(String id) {
        subscriptionClient.deleteSubscription(id);
    }
}
