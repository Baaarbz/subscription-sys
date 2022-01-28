package dev.barbz.subscriptionsyscore.domain.util;

import dev.barbz.subscriptionsyscore.application.request.CreateSubscriptionRequest;
import dev.barbz.subscriptionsyscore.application.response.SubscriptionResponse;
import dev.barbz.subscriptionsyscore.domain.Subscription;
import dev.barbz.subscriptionsyscore.domain.exception.SubscriptionBadRequestException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static dev.barbz.subscriptionsyscore.domain.util.SubscriptionConstants.EMAIL_PATTERN;
import static dev.barbz.subscriptionsyscore.domain.util.SubscriptionConstants.NO_VALID_FIELD_EXCEPTION;
import static java.util.Objects.isNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubscriptionUtil {

    public static Subscription instantiateDomainSubscription(CreateSubscriptionRequest subscriptionRequest) {
        validateFields(subscriptionRequest);
        return new Subscription()
                .firstName(subscriptionRequest.firstName())
                .lastName(subscriptionRequest.lastName())
                .email(subscriptionRequest.email())
                .gender(subscriptionRequest.gender())
                .birthday(subscriptionRequest.birthday())
                .consent(subscriptionRequest.consent())
                .campaign(subscriptionRequest.campaign());
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

    private static void validateFields(CreateSubscriptionRequest subscriptionRequest) {
        Pattern emailPattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

        if (isNull(subscriptionRequest.campaign()) || subscriptionRequest.campaign().length() == 0) {
            throw new SubscriptionBadRequestException(NO_VALID_FIELD_EXCEPTION, "campaign field can not be null or empty");
        }
        if (isNull(subscriptionRequest.firstName()) || subscriptionRequest.firstName().length() == 0) {
            throw new SubscriptionBadRequestException(NO_VALID_FIELD_EXCEPTION, "firstName field can not be null or empty");
        }
        if (isNull(subscriptionRequest.birthday())) {
            throw new SubscriptionBadRequestException(NO_VALID_FIELD_EXCEPTION, "birthday field can not be null");
        }
        if (isNull(subscriptionRequest.consent()) || !subscriptionRequest.consent()) {
            throw new SubscriptionBadRequestException(NO_VALID_FIELD_EXCEPTION, "consent field can not be null or false, we must have explicit consent to send mails");
        }
        if (isNull(subscriptionRequest.email()) || !emailPattern.matcher(subscriptionRequest.email()).matches()) {
            throw new SubscriptionBadRequestException(NO_VALID_FIELD_EXCEPTION, "email field can not be null or no valid format");
        }
        if (isNull(subscriptionRequest.gender())) {
            throw new SubscriptionBadRequestException(NO_VALID_FIELD_EXCEPTION, "gender field can not be null");
        }
    }
}
