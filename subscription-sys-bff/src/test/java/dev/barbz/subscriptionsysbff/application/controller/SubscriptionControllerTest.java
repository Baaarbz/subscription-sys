package dev.barbz.subscriptionsysbff.application.controller;

import dev.barbz.subscriptionsysbff.application.request.RegisterSubscriptionRequest;
import dev.barbz.subscriptionsysbff.application.response.SubscriptionResponse;
import dev.barbz.subscriptionsysbff.domain.enums.Gender;
import dev.barbz.subscriptionsysbff.domain.service.SubscriptionServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URISyntaxException;
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

    @SpyBean
    SubscriptionController subscriptionController;

    @MockBean
    SubscriptionServiceImpl subscriptionService;

    static SubscriptionResponse subscriptionResponse;
    static RegisterSubscriptionRequest registerSubscriptionRequest;

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
    void retrieveAllSubscriptions_then_OK() {
        when(subscriptionService.retrieveAllSubscriptions())
                .thenReturn(
                        Collections.singletonList(subscriptionResponse)
                );

        ResponseEntity<List<SubscriptionResponse>> response = subscriptionController.retrieveAll();

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void retrieveAllSubscriptions_then_NO_CONTENT() {
        when(subscriptionService.retrieveAllSubscriptions())
                .thenReturn(
                        Collections.emptyList()
                );

        ResponseEntity<List<SubscriptionResponse>> response = subscriptionController.retrieveAll();

        assertNull(response.getBody());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void retrieveSubscription_then_OK() {
        when(subscriptionService.retrieveSubscription(anyString()))
                .thenReturn(subscriptionResponse);

        ResponseEntity<SubscriptionResponse> response = subscriptionController.retrieve("id-test");

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(subscriptionResponse, response.getBody());
    }

    @Test
    void registerSubscription_then_CREATED() throws URISyntaxException {
        when(subscriptionService.registerSubscription(any(RegisterSubscriptionRequest.class)))
                .thenReturn("id-test");

        ResponseEntity<Void> response = subscriptionController.register(registerSubscriptionRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("/v1/subscriptions/id-test", response.getHeaders().get("Location").get(0));
    }

    @Test
    void deleteSubscription_then_OK() {
        doNothing().when(subscriptionService).cancelSubscription(anyString());

        ResponseEntity<Void> response = subscriptionController.cancel("id-test");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }
}
