package dev.barbz.subscriptionsyscore.application.controller;

import dev.barbz.subscriptionsyscore.application.response.ErrorResponse;
import dev.barbz.subscriptionsyscore.domain.exception.SubscriptionBadRequestException;
import dev.barbz.subscriptionsyscore.domain.exception.SubscriptionNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class SubscriptionControllerAdviceTest {

    @SpyBean
    private SubscriptionControllerAdvice subscriptionControllerAdvice;

    @Test
    void handle_httpMessageNotReadableException_OK() {
        HttpMessageNotReadableException e = Mockito.mock(HttpMessageNotReadableException.class);
        ResponseEntity<ErrorResponse> response = subscriptionControllerAdvice.handleHttpMessageNotReadableException(e);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void handle_subscriptionBadRequestException_OK() {
        SubscriptionBadRequestException e = Mockito.mock(SubscriptionBadRequestException.class);
        ResponseEntity<ErrorResponse> response = subscriptionControllerAdvice.handleSubscriptionBadRequestException(e);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void handle_subscriptionNotFoundException_OK() {
        SubscriptionNotFoundException e = Mockito.mock(SubscriptionNotFoundException.class);
        ResponseEntity<ErrorResponse> response = subscriptionControllerAdvice.handleSubscriptionNotFoundException(e);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
