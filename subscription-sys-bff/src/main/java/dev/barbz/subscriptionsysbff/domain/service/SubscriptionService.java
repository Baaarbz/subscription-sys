package dev.barbz.subscriptionsysbff.domain.service;

import dev.barbz.subscriptionsysbff.application.request.RegisterSubscriptionRequest;
import dev.barbz.subscriptionsysbff.application.response.SubscriptionResponse;

import java.util.List;

public interface SubscriptionService {

    String registerSubscription(RegisterSubscriptionRequest registerSubscription);

    List<SubscriptionResponse> retrieveAllSubscriptions();

    SubscriptionResponse retrieveSubscription(String id);

    void cancelSubscription(String id);
}
