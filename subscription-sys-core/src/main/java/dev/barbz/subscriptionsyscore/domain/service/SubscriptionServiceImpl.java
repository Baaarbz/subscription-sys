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

import static dev.barbz.subscriptionsyscore.domain.util.SubscriptionUtil.mapDomainSubscription;
import static dev.barbz.subscriptionsyscore.domain.util.SubscriptionUtil.mapSubscriptionResponse;
import static dev.barbz.subscriptionsyscore.domain.util.SubscriptionUtil.mapSubscriptionResponseList;


@Service
public record SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                                      MessageQueue messageQueue) implements SubscriptionService {

    @Override
    public SubscriptionResponse create(CreateSubscriptionRequest createSubscription) {
        Subscription subscription = mapDomainSubscription(createSubscription);
        subscriptionRepository.save(subscription);

        // Send queue message to send mail notification
        messageQueue.sendNewSubscriptionMessage(subscription);

        return mapSubscriptionResponse(subscription);
    }

    @Override
    public List<SubscriptionResponse> retrieveAll() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();

        return mapSubscriptionResponseList(subscriptions);
    }

    @Override
    public SubscriptionResponse retrieveById(String id) {
        Subscription subscription = retrieveSubscription(id);
        return mapSubscriptionResponse(subscription);
    }

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
