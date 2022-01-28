package dev.barbz.subscriptionsysbff.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.barbz.subscriptionsysbff.application.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static java.util.Objects.nonNull;

@Slf4j
@ControllerAdvice
public record SubscriptionControllerAdvice() {

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("INVALID_BODY_REQUEST", e.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<ErrorResponse> handleWebClientResponseException(WebClientResponseException e) {
        try {
            ErrorResponse errorResponse = new ObjectMapper().readValue(e.getResponseBodyAsString(), ErrorResponse.class);
            if (nonNull(e.getRequest())) {
                log.error("{}: {}  |  {}", e.getRequest().getMethod(), e.getRequest().getURI(), errorResponse.detailedMessage());
            }
            return ResponseEntity
                    .status(e.getRawStatusCode())
                    .body(errorResponse);
        } catch (JsonProcessingException exc) {
            return ResponseEntity
                    .status(e.getRawStatusCode())
                    .body(new ErrorResponse("SERVICE_ERROR", e.getResponseBodyAsString()));
        }
    }
}
