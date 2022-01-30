package dev.barbz.subscriptionsysbff.infrastructure.client;

import dev.barbz.subscriptionsysbff.domain.Subscription;
import dev.barbz.subscriptionsysbff.domain.client.SubscriptionClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * Implementation of the client definition.
 */
@Component
public class SubscriptionClientImpl implements SubscriptionClient {

    private final static String PATH_PARAM_SUBSCRIPTION_ID = "{subscriptionId}";

    private final WebClient subscriptionCoreClient;

    @Value("${services.subscription-sys-core.endpoints.create}")
    private String createPath;
    @Value("${services.subscription-sys-core.endpoints.retrieve-all}")
    private String retrieveAllPath;
    @Value("${services.subscription-sys-core.endpoints.retrieve-by-id}")
    private String retrieveByIdPath;
    @Value("${services.subscription-sys-core.endpoints.delete}")
    private String deletePath;

    /**
     * Subscription client constructor.
     *
     * @param subscriptionCoreClient web client configured to consume the subscription system core.
     */
    public SubscriptionClientImpl(WebClient subscriptionCoreClient) {
        this.subscriptionCoreClient = subscriptionCoreClient;
    }

    /**
     * Request to register a subscription in the system.
     *
     * @param subscription subscription to register.
     * @return saved subscription with the field id updated.
     */
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

    /**
     * Request to get a subscription by its id.
     *
     * @param subscriptionId subscription id.
     * @return subscription found.
     */
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

    /**
     * Request to get all registered subscriptions in the system.
     *
     * @return list of subscriptions
     */
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

    /**
     * Request to delete the subscription by its id.
     *
     * @param subscriptionId subscription id to delete.
     */
    @Override
    public void deleteSubscription(String subscriptionId) {
        final String path = deletePath.replace(PATH_PARAM_SUBSCRIPTION_ID, subscriptionId);

        subscriptionCoreClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .path(path)
                        .build())
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
