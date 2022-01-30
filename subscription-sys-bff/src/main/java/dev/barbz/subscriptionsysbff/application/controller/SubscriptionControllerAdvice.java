package dev.barbz.subscriptionsysbff.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.barbz.subscriptionsysbff.application.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static java.util.Objects.nonNull;

/**
 * Controller advice to handle thrown exceptions by the microservice.
 */
@Slf4j
@RestControllerAdvice
public class SubscriptionControllerAdvice {

    /**
     * Handle of the exception {@link HttpMessageNotReadableException} thrown when the received body in the
     * request is not valid.
     *
     * @param e http message not readable exception
     * @return bad request with description of error.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("INVALID_BODY_REQUEST", e.getMessage()));
    }

    /**
     * Handle of the exception {@link WebClientResponseException} thrown when the consumed microservice return an error,
     * it can be 4XX, 5XX
     *
     * @param e web client response exception
     * @return propagate the status received from the microservice and the error.
     */
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
