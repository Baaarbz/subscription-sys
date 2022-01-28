package dev.barbz.subscriptionsysbff.domain.util;

import dev.barbz.subscriptionsysbff.application.request.RegisterSubscriptionRequest;
import dev.barbz.subscriptionsysbff.application.response.SubscriptionResponse;
import dev.barbz.subscriptionsysbff.domain.Subscription;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubscriptionUtil {

    public static Subscription instantiateDomainSubscription(RegisterSubscriptionRequest subscriptionRequest) {
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

    public static List<SubscriptionResponse> instantiateSubscriptionResponseList(List<Subscription> subscriptions) {
        List<SubscriptionResponse> subscriptionResponseList = new ArrayList<>();

        for (Subscription subscription : subscriptions) {
            SubscriptionResponse subscriptionResponse = instantiateSubscriptionResponse(subscription);
            subscriptionResponseList.add(subscriptionResponse);
        }

        return subscriptionResponseList;
    }

    public static SubscriptionResponse instantiateSubscriptionResponse(Subscription subscription) {
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
