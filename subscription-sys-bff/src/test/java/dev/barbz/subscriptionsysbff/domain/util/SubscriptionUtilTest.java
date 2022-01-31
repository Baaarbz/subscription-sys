package dev.barbz.subscriptionsysbff.domain.util;

import dev.barbz.subscriptionsysbff.application.request.RegisterSubscriptionRequest;
import dev.barbz.subscriptionsysbff.application.response.SubscriptionResponse;
import dev.barbz.subscriptionsysbff.domain.Subscription;
import dev.barbz.subscriptionsysbff.domain.enums.Gender;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class SubscriptionUtilTest {


    private static Subscription subscription;
    private static RegisterSubscriptionRequest registerSubscription;

    @BeforeAll
    static void setUp() {
        subscription = new Subscription(
                null,
                "name",
                "lastName",
                "email@mail.com",
                Gender.NA,
                null,
                true,
                "test");

        registerSubscription = new RegisterSubscriptionRequest(
                "name",
                "lastName",
                "email@mail.com",
                Gender.NA,
                null,
                true,
                "test"
        );
    }

    @Test
    void mapToDomainSubscription_OK() {
        Subscription mappedSubscription = SubscriptionUtil.mapToDomainSubscription(registerSubscription);

        assertNull(mappedSubscription.id());
        assertEquals(subscription, mappedSubscription);
    }

    @Test
    void mapToSubscriptionResponseList_OK() {
        List<SubscriptionResponse> subscriptionResponse = SubscriptionUtil
                .mapToSubscriptionResponseList(Collections.singletonList(subscription));

        assertNotNull(subscriptionResponse);
        assertEquals(1, subscriptionResponse.size());
    }

    @Test
    void mapToSubscriptionResponse_OK() {
        SubscriptionResponse subscriptionResponse = SubscriptionUtil
                .mapToSubscriptionResponse(subscription);

        assertNotNull(subscriptionResponse);
    }
}
