package dev.barbz.subscriptionsyscore.application.controller;

import dev.barbz.subscriptionsyscore.application.request.CreateSubscriptionRequest;
import dev.barbz.subscriptionsyscore.application.response.SubscriptionResponse;
import dev.barbz.subscriptionsyscore.domain.enums.Gender;
import dev.barbz.subscriptionsyscore.domain.service.SubscriptionServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SubscriptionControllerTest {

    static SubscriptionResponse subscriptionResponse;
    static CreateSubscriptionRequest createSubscriptionRequest;
    @SpyBean
    SubscriptionController subscriptionController;
    @MockBean
    SubscriptionServiceImpl subscriptionService;

    @BeforeAll
    static void setUp() {
        subscriptionResponse = new SubscriptionResponse(
                "test",
                "test name",
                "test last name",
                "test@mail.com",
                Gender.NA,
                LocalDate.now(),
                true,
                "test campaign"
        );

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
    void retrieveAllSubscriptions_then_OK() {
        when(subscriptionService.retrieveAll())
                .thenReturn(
                        Collections.singletonList(subscriptionResponse)
                );

        ResponseEntity<List<SubscriptionResponse>> response = subscriptionController.retrieveAll();

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void retrieveSubscription_then_OK() {
        when(subscriptionService.retrieveById(anyString()))
                .thenReturn(subscriptionResponse);

        ResponseEntity<SubscriptionResponse> response = subscriptionController.retrieve("id-test");

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(subscriptionResponse, response.getBody());
    }

    @Test
    void registerSubscription_then_CREATED() {
        when(subscriptionService.create(any(CreateSubscriptionRequest.class)))
                .thenReturn(subscriptionResponse);

        ResponseEntity<SubscriptionResponse> response = subscriptionController.register(createSubscriptionRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void deleteSubscription_then_OK() {
        doNothing().when(subscriptionService).delete(anyString());

        ResponseEntity<Void> response = subscriptionController.cancel("id-test");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }
}
