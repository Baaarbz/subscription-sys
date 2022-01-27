package dev.barbz.subscriptionsysbff.infrastructure.client;

import dev.barbz.subscriptionsysbff.domain.Subscription;
import dev.barbz.subscriptionsysbff.domain.client.SubscriptionClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class SubscriptionClientImpl implements SubscriptionClient {

    private final static String PATH_PARAM_SUBSCRIPTION_ID = "{subscriptionId}";

    @Value("${services.subscription-sys-core.endpoints.create}")
    private String createPath;

    @Value("${services.subscription-sys-core.endpoints.retrieve-all}")
    private String retrieveAllPath;

    @Value("${services.subscription-sys-core.endpoints.retrieve-by-id}")
    private String retrieveByIdPath;

    @Value("${services.subscription-sys-core.endpoints.delete}")
    private String delete;

    private final WebClient subscriptionCoreClient;

    public SubscriptionClientImpl(WebClient subscriptionCoreClient) {
        this.subscriptionCoreClient = subscriptionCoreClient;
    }

    @Override
    public Subscription createSubscription(Subscription subscription) {
        return subscriptionCoreClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(createPath)
                        .build())
                .body(BodyInserters.fromValue(subscription))
                .retrieve()
                .bodyToMono(Subscription.class)
                .block();
    }

    @Override
    public Subscription getSubscriptionById(String subscriptionId) {
        final String path = retrieveByIdPath.replace(PATH_PARAM_SUBSCRIPTION_ID, subscriptionId);

        return subscriptionCoreClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(path)
                        .build())
                .retrieve()
                .bodyToMono(Subscription.class)
                .block();
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionCoreClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(retrieveAllPath)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Subscription>>() {
                })
                .block();
    }

    @Override
    public void deleteSubscription(String subscriptionId) {
        subscriptionCoreClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .path(delete)
                        .build())
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
