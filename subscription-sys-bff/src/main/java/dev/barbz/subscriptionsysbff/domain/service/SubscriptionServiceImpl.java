package dev.barbz.subscriptionsysbff.domain.service;

import dev.barbz.subscriptionsysbff.application.request.RegisterSubscriptionRequest;
import dev.barbz.subscriptionsysbff.application.response.SubscriptionResponse;
import dev.barbz.subscriptionsysbff.domain.Subscription;
import dev.barbz.subscriptionsysbff.domain.client.SubscriptionClient;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.barbz.subscriptionsysbff.domain.util.SubscriptionUtil.*;

@Service
public record SubscriptionServiceImpl(SubscriptionClient subscriptionClient) implements SubscriptionService {

    @Override
    public String registerSubscription(RegisterSubscriptionRequest registerSubscription) {
        Subscription subscription = instantiateDomainSubscription(registerSubscription);

        return subscriptionClient.createSubscription(subscription).id();
    }

    @Override
    public List<SubscriptionResponse> retrieveAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionClient.getAllSubscriptions();

        return instantiateSubscriptionResponseList(subscriptions);
    }

    @Override
    public SubscriptionResponse retrieveSubscription(String id) {
        Subscription subscription = subscriptionClient.getSubscriptionById(id);

        return instantiateSubscriptionResponse(subscription);
    }

    @Override
    public void cancelSubscription(String id) {
        subscriptionClient.deleteSubscription(id);
    }
}
