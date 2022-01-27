package dev.barbz.subscriptionsysbff.domain.service;

import dev.barbz.subscriptionsysbff.application.request.RegisterSubscriptionRequest;
import dev.barbz.subscriptionsysbff.application.response.SubscriptionResponse;
import dev.barbz.subscriptionsysbff.application.response.SubscriptionsResponse;

public interface SubscriptionService {

    String registerSubscription(RegisterSubscriptionRequest registerSubscription);

    SubscriptionsResponse retrieveAll();

    SubscriptionResponse retrieve(String id);

    void cancel(String id);
}
