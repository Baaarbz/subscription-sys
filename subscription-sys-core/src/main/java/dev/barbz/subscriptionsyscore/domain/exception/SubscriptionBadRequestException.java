package dev.barbz.subscriptionsyscore.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Bad Request exception, thrown when the received data is invalid. This exception will
 * fire the controller advice, returning a Bad Request with the detailed info.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SubscriptionBadRequestException extends RuntimeException {

    private String detailedMessage;

    /**
     * Subscription Bad Request exception constructor
     *
     * @param message         short message of the error
     * @param detailedMessage detailed message about the error.
     */
    public SubscriptionBadRequestException(String message, String detailedMessage) {
        super(message);
        this.detailedMessage = detailedMessage;
    }
}
