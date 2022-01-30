package dev.barbz.subscriptionsysbff.domain.util;

import dev.barbz.subscriptionsysbff.application.request.RegisterSubscriptionRequest;
import dev.barbz.subscriptionsysbff.application.response.SubscriptionResponse;
import dev.barbz.subscriptionsysbff.domain.Subscription;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Subscription utility class.
 * This utility class contains methods to map different kind of objects.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubscriptionUtil {

    /**
     * Mapper function to obtain a {@link Subscription} object from the {@link RegisterSubscriptionRequest}.
     *
     * @param subscriptionRequest register subscription request.
     * @return subscription domain object.
     */
    public static Subscription mapToDomainSubscription(RegisterSubscriptionRequest subscriptionRequest) {
        return new Subscription(
                null,
                subscriptionRequest.firstName(),
                subscriptionRequest.lastName(),
                subscriptionRequest.email(),
                subscriptionRequest.gender(),
                subscriptionRequest.birthday(),
                subscriptionRequest.consent(),
                subscriptionRequest.campaign()
        );
    }

    /**
     * Mapper function to obtain a list of {@link RegisterSubscriptionRequest} receiving the list of {@link Subscription}
     *
     * @param subscriptions list of subscriptions.
     * @return list of subscriptions response.
     */
    public static List<SubscriptionResponse> mapToSubscriptionResponseList(List<Subscription> subscriptions) {
        List<SubscriptionResponse> subscriptionResponseList = new ArrayList<>();

        for (Subscription subscription : subscriptions) {
            SubscriptionResponse subscriptionResponse = mapToSubscriptionResponse(subscription);
            subscriptionResponseList.add(subscriptionResponse);
        }

        return subscriptionResponseList;
    }

    /**
     * Mapper function to obtain a {@link RegisterSubscriptionRequest} object from the {@link Subscription}.
     *
     * @param subscription subscription domain object
     * @return subscription response object.
     */
    public static SubscriptionResponse mapToSubscriptionResponse(Subscription subscription) {
        return new SubscriptionResponse(
                subscription.id(),
                subscription.firstName(),
                subscription.lastName(),
                subscription.email(),
                subscription.gender(),
                subscription.birthday(),
                subscription.consent(),
                subscription.campaign()
        );
    }
}
