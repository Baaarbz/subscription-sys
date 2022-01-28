package dev.barbz.subscriptionsyscore.domain.util;

import dev.barbz.subscriptionsyscore.application.request.CreateSubscriptionRequest;
import dev.barbz.subscriptionsyscore.application.response.SubscriptionResponse;
import dev.barbz.subscriptionsyscore.domain.Subscription;
import dev.barbz.subscriptionsyscore.domain.exception.SubscriptionBadRequestException;
import dev.barbz.subscriptionsyscore.domain.messaging.SubscriptionMessage;
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

    public static Subscription mapToDomainSubscription(CreateSubscriptionRequest subscriptionRequest) {
        validateFields(subscriptionRequest);
        return new Subscription()
                .setFirstName(subscriptionRequest.firstName())
                .setLastName(subscriptionRequest.lastName())
                .setEmail(subscriptionRequest.email())
                .setGender(subscriptionRequest.gender())
                .setBirthday(subscriptionRequest.birthday())
                .setConsent(subscriptionRequest.consent())
                .setCampaign(subscriptionRequest.campaign());
    }

    public static List<SubscriptionResponse> mapToSubscriptionResponseList(List<Subscription> subscriptions) {
        List<SubscriptionResponse> subscriptionResponseList = new ArrayList<>();

        for (Subscription subscription : subscriptions) {
            SubscriptionResponse subscriptionResponse = mapToSubscriptionResponse(subscription);
            subscriptionResponseList.add(subscriptionResponse);
        }

        return subscriptionResponseList;
    }

    public static SubscriptionResponse mapToSubscriptionResponse(Subscription subscription) {
        return new SubscriptionResponse(
                subscription.getId(),
                subscription.getFirstName(),
                subscription.getLastName(),
                subscription.getEmail(),
                subscription.getGender(),
                subscription.getBirthday(),
                subscription.getConsent(),
                subscription.getCampaign()
        );
    }

    public static SubscriptionMessage mapToSubscriptionMessage(Subscription subscription) {
        return new SubscriptionMessage()
                .setFirstName(subscription.getFirstName())
                .setLastName(subscription.getLastName())
                .setEmail(subscription.getEmail())
                .setGender(subscription.getGender())
                .setCampaign(subscription.getCampaign());
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
