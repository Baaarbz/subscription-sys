package dev.barbz.subscriptionsyscore.domain.repository;

import dev.barbz.subscriptionsyscore.domain.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository {

    Subscription save(Subscription subscription);

    Optional<Subscription> findById(String subscriptionId);

    List<Subscription> findAll();

    void delete(Subscription subscription);
}
