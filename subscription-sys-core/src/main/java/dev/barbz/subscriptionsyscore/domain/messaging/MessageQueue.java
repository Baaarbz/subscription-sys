package dev.barbz.subscriptionsyscore.domain.messaging;

import dev.barbz.subscriptionsyscore.domain.Subscription;

public interface MessageQueue {

    void sendNewSubscriptionMessage(Subscription subscription);
}
