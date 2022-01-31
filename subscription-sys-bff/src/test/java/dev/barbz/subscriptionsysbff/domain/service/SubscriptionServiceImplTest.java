package dev.barbz.subscriptionsysbff.domain.service;

import dev.barbz.subscriptionsysbff.application.request.RegisterSubscriptionRequest;
import dev.barbz.subscriptionsysbff.application.response.SubscriptionResponse;
import dev.barbz.subscriptionsysbff.domain.Subscription;
import dev.barbz.subscriptionsysbff.domain.client.SubscriptionClient;
import dev.barbz.subscriptionsysbff.domain.enums.Gender;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SubscriptionServiceImplTest {

    static RegisterSubscriptionRequest registerSubscriptionRequest;
    static Subscription subscription;
    @SpyBean
    SubscriptionServiceImpl subscriptionService;
    @MockBean
    SubscriptionClient subscriptionClient;

    @BeforeAll
    static void setUp() {
        subscription = new Subscription(
                "id-test",
                "test name",
                "test last name",
                "test@mail.com",
                Gender.NA,
                LocalDate.now(),
                true,
                "test campaign"
        );

        registerSubscriptionRequest = new RegisterSubscriptionRequest(
                "test name",
                "test last name",
                "test@mail.com",
                Gender.NA,
                LocalDate.now(),
                true,
                "test campaign"
        );
    }

    @Test
    void registerSubscription_then_OK() {
        when(subscriptionClient.createSubscription(any(Subscription.class)))
                .thenReturn(subscription);

        String subscriptionId = subscriptionService.registerSubscription(registerSubscriptionRequest);

        assertEquals(subscription.id(), subscriptionId);
    }

    @Test
    void retrieveAllSubscriptions_then_OK() {
        when(subscriptionClient.getAllSubscriptions())
                .thenReturn(
                        Collections.singletonList(subscription)
                );

        List<SubscriptionResponse> subscriptions = subscriptionService.retrieveAllSubscriptions();

        assertEquals(1, subscriptions.size());
    }

    @Test
    void retrieveSubscription_then_OK() {
        when(subscriptionClient.getSubscriptionById(anyString()))
                .thenReturn(subscription);

        SubscriptionResponse subscriptionResponse = subscriptionService.retrieveSubscription("id-test");

        assertNotNull(subscriptionResponse);
    }

    @Test
    void cancelSubscription_then_OK() {
        doNothing().when(subscriptionClient).deleteSubscription(anyString());

        assertDoesNotThrow(() -> subscriptionService.cancelSubscription("id-test"));
    }
}
