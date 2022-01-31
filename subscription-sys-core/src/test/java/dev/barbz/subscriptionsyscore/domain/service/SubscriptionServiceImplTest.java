package dev.barbz.subscriptionsyscore.domain.service;

import dev.barbz.subscriptionsyscore.application.request.CreateSubscriptionRequest;
import dev.barbz.subscriptionsyscore.application.response.SubscriptionResponse;
import dev.barbz.subscriptionsyscore.domain.Subscription;
import dev.barbz.subscriptionsyscore.domain.enums.Gender;
import dev.barbz.subscriptionsyscore.domain.exception.SubscriptionNotFoundException;
import dev.barbz.subscriptionsyscore.infrastructure.messaging.MessageQueueImpl;
import dev.barbz.subscriptionsyscore.infrastructure.repository.SubscriptionRepositoryImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SubscriptionServiceImplTest {

    static CreateSubscriptionRequest createSubscriptionRequest;
    static Subscription subscription;
    @SpyBean
    SubscriptionServiceImpl subscriptionService;
    @MockBean
    SubscriptionRepositoryImpl subscriptionRepository;
    @MockBean
    MessageQueueImpl messageQueue;

    @BeforeAll
    static void setUp() {
        subscription = new Subscription()
                .setId(null)
                .setFirstName("name")
                .setLastName("lastName")
                .setEmail("email@mail.com")
                .setGender(Gender.NA)
                .setBirthday(LocalDate.now())
                .setConsent(true)
                .setCampaign("test");

        createSubscriptionRequest = new CreateSubscriptionRequest(
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
    void create_then_OK() {
        when(subscriptionRepository.save(any(Subscription.class)))
                .thenReturn(subscription);

        doNothing().when(messageQueue).sendNewSubscriptionMessage(subscription);

        assertNotNull(subscriptionService.create(createSubscriptionRequest));
    }

    @Test
    void retrieveAll_then_OK() {
        when(subscriptionRepository.findAll())
                .thenReturn(Collections.singletonList(subscription));

        List<SubscriptionResponse> subscriptions = subscriptionService.retrieveAll();

        assertEquals(1, subscriptions.size());
    }

    @Test
    void retrieveById_then_OK() {
        when(subscriptionRepository.findById(anyString()))
                .thenReturn(Optional.of(subscription));

        SubscriptionResponse subscriptionResponse = subscriptionService.retrieveById("id-test");

        assertNotNull(subscriptionResponse);
    }

    @Test
    void retrieveById_then_throwsNotFound() {
        when(subscriptionRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        assertThrows(SubscriptionNotFoundException.class, () -> subscriptionService.retrieveById("id-test"));
    }

    @Test
    void delete_then_OK() {
        when(subscriptionRepository.findById(anyString()))
                .thenReturn(Optional.of(subscription));
        doNothing().when(subscriptionRepository).delete(subscription);

        assertDoesNotThrow(() -> subscriptionService.delete("id-test"));
    }

    @Test
    void delete_then_throwsNotFound() {
        when(subscriptionRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        assertThrows(SubscriptionNotFoundException.class, () -> subscriptionService.delete("id-test"));
    }
}
