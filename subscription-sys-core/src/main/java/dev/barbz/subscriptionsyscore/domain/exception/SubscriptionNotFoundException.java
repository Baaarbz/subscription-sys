package dev.barbz.subscriptionsyscore.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Not found exception, thrown when the subscription requested by its id is not found in the system. This exception will
 * fire the controller advice, returning a Not Found with the detailed info.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SubscriptionNotFoundException extends RuntimeException {

    /**
     * Subscription not found exception constructor.
     *
     * @param message short message about the exception.
     */
    public SubscriptionNotFoundException(String message) {
        super(message);
    }

}
