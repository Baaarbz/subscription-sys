package dev.barbz.subscriptionsyscore.domain.service;

import dev.barbz.subscriptionsyscore.application.request.CreateSubscriptionRequest;
import dev.barbz.subscriptionsyscore.application.response.SubscriptionResponse;
import dev.barbz.subscriptionsyscore.domain.Subscription;
import dev.barbz.subscriptionsyscore.domain.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static dev.barbz.subscriptionsyscore.domain.util.SubscriptionUtil.instantiateDomainSubscription;
import static dev.barbz.subscriptionsyscore.domain.util.SubscriptionUtil.instantiateSubscriptionResponse;
import static dev.barbz.subscriptionsyscore.domain.util.SubscriptionUtil.instantiateSubscriptionResponseList;


@Service
public record SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) implements SubscriptionService {

    @Override
    public SubscriptionResponse create(CreateSubscriptionRequest createSubscription) {
        Subscription subscription = instantiateDomainSubscription(createSubscription);
        subscriptionRepository.save(subscription);

        return instantiateSubscriptionResponse(subscription);
    }

    @Override
    public List<SubscriptionResponse> retrieveAll() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();

        return instantiateSubscriptionResponseList(subscriptions);
    }

    @Override
    public SubscriptionResponse retrieveById(String id) {
        Subscription subscription = retrieveSubscription(id);
        return instantiateSubscriptionResponse(subscription);
    }

    @Override
    public void delete(String id) {
        Subscription subscription = retrieveSubscription(id);
        subscriptionRepository.delete(subscription);
    }

    private Subscription retrieveSubscription(String id) {
        Optional<Subscription> optionalSubscription = subscriptionRepository.findById(id);

        return optionalSubscription.orElseThrow();
    }
}
