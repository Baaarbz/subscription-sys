package dev.barbz.subscriptionsysbff.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.barbz.subscriptionsysbff.application.response.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.nio.charset.StandardCharsets;

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
    void handle_webClientResponseException_withErrorResponseError_OK() throws JsonProcessingException {
        String errorResponse = new ObjectMapper()
                .writeValueAsString(new ErrorResponse("test-error", "detaile-message"));
        WebClientResponseException e = new WebClientResponseException(
                400,
                "BAD REQUEST",
                new HttpHeaders(),
                errorResponse.getBytes(),
                StandardCharsets.UTF_8
        );
        ResponseEntity<ErrorResponse> response = subscriptionControllerAdvice.handleWebClientResponseException(e);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void handle_webClientResponseException_randomBodyError_OK() throws JsonProcessingException {
        String errorResponse = "test-error";
        WebClientResponseException e = new WebClientResponseException(
                400,
                "BAD REQUEST",
                new HttpHeaders(),
                errorResponse.getBytes(),
                StandardCharsets.UTF_8
        );
        ResponseEntity<ErrorResponse> response = subscriptionControllerAdvice.handleWebClientResponseException(e);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errorResponse, response.getBody().detailedMessage());
    }
}
