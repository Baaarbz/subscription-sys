package dev.barbz.subscriptionsyscore.domain.service;

import dev.barbz.subscriptionsyscore.application.request.CreateSubscriptionRequest;
import dev.barbz.subscriptionsyscore.application.response.SubscriptionResponse;

import java.util.List;

public interface SubscriptionService {

    SubscriptionResponse create(CreateSubscriptionRequest registerSubscription);

    List<SubscriptionResponse> retrieveAll();

    SubscriptionResponse retrieveById(String id);

    void delete(String id);
}
