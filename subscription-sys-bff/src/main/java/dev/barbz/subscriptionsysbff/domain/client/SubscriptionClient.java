package dev.barbz.subscriptionsysbff.domain.client;

import dev.barbz.subscriptionsysbff.domain.Subscription;

import java.util.List;

public interface SubscriptionClient {

    Subscription createSubscription(Subscription subscription);

    Subscription getSubscriptionById(String subscriptionId);

    List<Subscription> getAllSubscriptions();

    void deleteSubscription(String subscriptionId);
}
