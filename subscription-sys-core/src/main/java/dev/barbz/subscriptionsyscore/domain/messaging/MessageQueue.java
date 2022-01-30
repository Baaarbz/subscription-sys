package dev.barbz.subscriptionsyscore.domain.messaging;

import dev.barbz.subscriptionsyscore.domain.Subscription;

/**
 * Definition of the message queue contract.
 */
public interface MessageQueue {

    /**
     * A subscription message will be sent to the queue, where the mail service will be listening to notify the user
     * sending him a mail.
     *
     * @param subscription subscription data which will be mapped to {@link SubscriptionMessage}
     */
    void sendNewSubscriptionMessage(Subscription subscription);
}
