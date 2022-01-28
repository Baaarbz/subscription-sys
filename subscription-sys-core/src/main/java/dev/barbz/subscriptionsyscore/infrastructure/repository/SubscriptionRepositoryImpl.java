package dev.barbz.subscriptionsyscore.infrastructure.repository;

import dev.barbz.subscriptionsyscore.domain.Subscription;
import dev.barbz.subscriptionsyscore.domain.repository.SubscriptionRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public record SubscriptionRepositoryImpl(SubscriptionMongoRepository repository) implements SubscriptionRepository {

    @Override
    public Subscription save(Subscription subscription) {
        return repository.save(subscription);
    }

    @Override
    public Optional<Subscription> findById(String subscriptionId) {
        return repository.findById(subscriptionId);
    }

    @Override
    public List<Subscription> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Subscription subscription) {
        repository.delete(subscription);
    }
}
