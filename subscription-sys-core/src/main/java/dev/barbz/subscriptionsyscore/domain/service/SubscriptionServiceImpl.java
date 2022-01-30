package dev.barbz.subscriptionsyscore.domain.service;

import dev.barbz.subscriptionsyscore.application.request.CreateSubscriptionRequest;
import dev.barbz.subscriptionsyscore.application.response.SubscriptionResponse;
import dev.barbz.subscriptionsyscore.domain.Subscription;
import dev.barbz.subscriptionsyscore.domain.exception.SubscriptionNotFoundException;
import dev.barbz.subscriptionsyscore.domain.messaging.MessageQueue;
import dev.barbz.subscriptionsyscore.domain.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static dev.barbz.subscriptionsyscore.domain.util.SubscriptionUtil.mapToDomainSubscription;
import static dev.barbz.subscriptionsyscore.domain.util.SubscriptionUtil.mapToSubscriptionResponse;
import static dev.barbz.subscriptionsyscore.domain.util.SubscriptionUtil.mapToSubscriptionResponseList;

/**
 * Implementation of the subscription service contract
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final MessageQueue messageQueue;

    /**
     * Subscription service constructor
     *
     * @param subscriptionRepository subscription repository
     * @param messageQueue           message queue
     */
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, MessageQueue messageQueue) {
        this.subscriptionRepository = subscriptionRepository;
        this.messageQueue = messageQueue;
    }

    /**
     * Register a new subscription in the system, once it is saved in the database, this function will send a message
     * to the queue to after the saving, the mail service will send a mail to the user subscribed.
     *
     * @param createSubscription subscription to register
     * @return subscription response.
     */
    @Override
    public SubscriptionResponse create(CreateSubscriptionRequest createSubscription) {
        Subscription subscription = mapToDomainSubscription(createSubscription);
        subscriptionRepository.save(subscription);

        // Send queue message to send mail notification
        messageQueue.sendNewSubscriptionMessage(subscription);

        return mapToSubscriptionResponse(subscription);
    }

    /**
     * Retrieve all the subscriptions registered in the system.
     *
     * @return list of subscriptions.
     */
    @Override
    public List<SubscriptionResponse> retrieveAll() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();

        return mapToSubscriptionResponseList(subscriptions);
    }

    /**
     * Retrieve a subscription by its id.
     *
     * @param id subscription requested id.
     * @return found subscription.
     */
    @Override
    public SubscriptionResponse retrieveById(String id) {
        Subscription subscription = retrieveSubscription(id);
        return mapToSubscriptionResponse(subscription);
    }

    /**
     * Delete a subscription from the system by its id.
     *
     * @param id subscription id
     */
    @Override
    public void delete(String id) {
        Subscription subscription = retrieveSubscription(id);
        subscriptionRepository.delete(subscription);
    }

    private Subscription retrieveSubscription(String id) {
        Optional<Subscription> optionalSubscription = subscriptionRepository.findById(id);

        return optionalSubscription.orElseThrow(() -> new SubscriptionNotFoundException("subscription not found with id: " + id));
    }
}
