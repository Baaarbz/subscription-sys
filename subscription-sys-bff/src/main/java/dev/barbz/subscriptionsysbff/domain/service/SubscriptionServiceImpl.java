package dev.barbz.subscriptionsysbff.domain.service;

import dev.barbz.subscriptionsysbff.application.request.RegisterSubscriptionRequest;
import dev.barbz.subscriptionsysbff.application.response.SubscriptionResponse;
import dev.barbz.subscriptionsysbff.application.response.SubscriptionsResponse;

public class SubscriptionServiceImpl implements SubscriptionService {

    @Override
    public String registerSubscription(RegisterSubscriptionRequest registerSubscription) {
        return null;
    }

    @Override
    public SubscriptionsResponse retrieveAll() {
        return null;
    }

    @Override
    public SubscriptionResponse retrieve(String id) {
        return null;
    }

    @Override
    public void cancel(String id) {

    }
}
