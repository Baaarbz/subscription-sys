package dev.barbz.subscriptionsyscore.application.controller;

import dev.barbz.subscriptionsyscore.application.response.ErrorResponse;
import dev.barbz.subscriptionsyscore.domain.exception.SubscriptionBadRequestException;
import dev.barbz.subscriptionsyscore.domain.exception.SubscriptionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller advice to handle thrown exceptions by the microservice.
 */
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
     * Handle of the exception {@link SubscriptionBadRequestException}. When it is fired the microservice will
     * return a bad request status with information about the error.
     *
     * @param e subscription bad request exception
     * @return bad request status with information about the error.
     */
    @ExceptionHandler(SubscriptionBadRequestException.class)
    public ResponseEntity<ErrorResponse> handleSubscriptionBadRequestException(SubscriptionBadRequestException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage(), e.getDetailedMessage()));
    }

    /**
     * Handle of the exception {@link SubscriptionNotFoundException}. When it is fired the microservice will
     * return a not found status with information about the error.
     *
     * @param e subscription not found exception
     * @return not found status with information about the error.
     */
    @ExceptionHandler(SubscriptionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSubscriptionNotFoundException(SubscriptionNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("SUBSCRIPTION_NOT_FOUND", e.getMessage()));
    }
}
